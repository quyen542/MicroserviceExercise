package org.example.paymentservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImp implements AccountService {

  @Autowired
  private AccountRepository accountRepo;

  @Autowired
  private ProductRepository productRepo;

  @Autowired
  private PaymentTransactionRepository paymentRepo;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String msg) {
    kafkaTemplate.send("transaction", msg);
  }

  @Autowired
  private ObjectMapper objectMapper;


  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public boolean debit(String accountNumber, BigDecimal amount) {
    Account a = accountRepo.findByAccountNumber(accountNumber);
    if (a == null) {
      throw new IllegalArgumentException("Account not found: " + accountNumber);
    }

    if (a.getBalance().compareTo(amount) < 0) {
      throw new InsufficientBalanceException("Insufficient balance for account: " + accountNumber);
    }
    a.setBalance(a.getBalance().subtract(amount));
    accountRepo.save(a);

    return true;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public boolean credit(String accountNumber, BigDecimal amount) {
    Account a = accountRepo.findByAccountNumber(accountNumber);
    a.setBalance(a.getBalance().add(amount));
    accountRepo.save(a);
    return true;
  }

  @Override
  @Transactional
  public void purchase(String sourceAccountNumber, String destinationAccountNo, Long productId) {
    Product product = productRepo.findById(productId)
        .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    Account sourceAccount = accountRepo.findByAccountNumber(sourceAccountNumber);
    Account destinationAccount = accountRepo.findByAccountNumber(destinationAccountNo);

    debit(sourceAccountNumber, product.getPrice());

    paymentRepo.save(
        PaymentTransaction.builder().transactionId(UUID.randomUUID()).amount(product.getPrice())
            .status(
                TransactionStatus.SUCCESS).createdAt(LocalDateTime.now())
            .paymentInfo(product.getName()).account(sourceAccount)
            .transactionType(TransactionType.DEBIT).build());

    PaymentTransaction result = null;

    try {
      credit(destinationAccountNo, product.getPrice());

      result = paymentRepo.save(
          PaymentTransaction.builder()
              .transactionId(UUID.randomUUID())
              .amount(product.getPrice())
              .status(TransactionStatus.SUCCESS)
              .createdAt(LocalDateTime.now())
              .paymentInfo(product.getName())
              .account(destinationAccount)
              .transactionType(TransactionType.CREDIT)
              .build()
      );
    } catch (Exception e) {
      throw new RuntimeException("Failed to credit destination account. Rolling back transaction.",
          e);
    }

//    sendMessage(
//        "Transaction Id:" + result.getTransactionId() + "Source Account: " + sourceAccountNumber
//            + ", Destination Account: " + destinationAccountNo
//            + ", Amount: -" + product.getPrice() + ", Status: " + TransactionStatus.SUCCESS
//            + ", PaymentInfo: " + product.getName());

    try {
      sourceAccount = accountRepo.findByAccountNumber(sourceAccountNumber);

      // Tạo JSON object dưới dạng String
      String message = objectMapper.writeValueAsString(new TransactionMessage(
          result.getTransactionId(), sourceAccountNumber, destinationAccountNo, product.getPrice(), sourceAccount.getBalance(), TransactionStatus.SUCCESS, LocalDateTime.now(), product.getName()
      ));

      System.out.println("Transaction: " + message);


      kafkaTemplate.send("transaction", message);

    } catch (Exception e) {
      System.err.println("Error sending Kafka message: " + e.getMessage());
    }

  }
}
