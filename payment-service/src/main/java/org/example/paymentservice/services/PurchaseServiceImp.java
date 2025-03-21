package org.example.paymentservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.OptimisticLockException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.example.paymentservice.entities.Account;
import org.example.paymentservice.entities.PaymentTransaction;
import org.example.paymentservice.entities.Product;
import org.example.paymentservice.entities.TransactionMessage;
import org.example.paymentservice.entities.TransactionStatus;
import org.example.paymentservice.entities.TransactionType;
import org.example.paymentservice.exception.InsufficientBalanceException;
import org.example.paymentservice.repositories.AccountRepository;
import org.example.paymentservice.repositories.PaymentTransactionRepository;
import org.example.paymentservice.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImp implements PurchaseService {

  @Autowired
  private AccountRepository accountRepo;

  @Autowired
  private ProductRepository productRepo;

  @Autowired
  private PaymentTransactionRepository paymentRepo;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  private final Logger logger = LoggerFactory.getLogger(PurchaseServiceImp.class);


  @Override
  @Transactional
  public boolean debit(String accountNumber, BigDecimal amount, Integer version) {
    Account a = accountRepo.findByAccountNumber(accountNumber);
    if (a == null) {
      throw new IllegalArgumentException("Account not found: " + accountNumber);
    }

    if (!a.getVersion().equals(version)) {
      throw new OptimisticLockException("Data is changed");
    }

    if (a.getBalance().compareTo(amount) < 0) {
      throw new InsufficientBalanceException("Insufficient balance for account: " + accountNumber);
    }
    a.setBalance(a.getBalance().subtract(amount));
    accountRepo.save(a);

    return true;
  }

  @Override
  @Transactional
  public boolean credit(String accountNumber, BigDecimal amount, Integer version) {
    Account a = accountRepo.findByAccountNumber(accountNumber);

    if (a == null) {
      throw new IllegalArgumentException("Account not found: " + accountNumber);
    }

    if (!a.getVersion().equals(version)) {
      throw new OptimisticLockException("Data is changed");
    }
    a.setBalance(a.getBalance().add(amount));
    accountRepo.save(a);
    return true;
  }

  @Override
  @Transactional
  public PaymentTransaction purchase(String sourceAccountNumber, String destinationAccountNo,
      Long productId,
      Integer version) {
    Product product = productRepo.findById(productId)
        .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    Account sourceAccount = accountRepo.findByAccountNumber(sourceAccountNumber);
    Account destinationAccount = accountRepo.findByAccountNumber(destinationAccountNo);

    debit(sourceAccountNumber, product.getPrice(), version);

    paymentRepo.save(
        PaymentTransaction.builder().transactionId(UUID.randomUUID()).amount(product.getPrice())
            .status(
                TransactionStatus.SUCCESS)
            .paymentInfo(product.getName()).account(sourceAccount)
            .transactionType(TransactionType.DEBIT).build());

    PaymentTransaction result;

    try {
      credit(destinationAccountNo, product.getPrice(), destinationAccount.getVersion());

      result = paymentRepo.save(
          PaymentTransaction.builder()
              .transactionId(UUID.randomUUID())
              .amount(product.getPrice())
              .status(TransactionStatus.SUCCESS)
              .paymentInfo(product.getName())
              .account(destinationAccount)
              .transactionType(TransactionType.CREDIT)
              .build()
      );
    } catch (Exception e) {
      throw new RuntimeException("Failed to credit destination account. Rolling back transaction.",
          e);
    }

    try {
      sourceAccount = accountRepo.findByAccountNumber(sourceAccountNumber);

      // Tạo JSON object dưới dạng String
      String message = objectMapper.writeValueAsString(new TransactionMessage(
          result.getTransactionId(), sourceAccountNumber, destinationAccountNo, product.getPrice(),
          sourceAccount.getBalance(), TransactionStatus.SUCCESS, LocalDateTime.now().withNano(0),
          product.getName()
      ));

      logger.info("Sending message to source account {}: {}", sourceAccountNumber, message);

      kafkaTemplate.send("transaction", message);

    } catch (Exception e) {
      logger.error("Failed to send transaction.", e);
    }

    return result;


  }
}
