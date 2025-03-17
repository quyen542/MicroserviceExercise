package org.example.vasservice.component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.example.vasservice.entities.TransactionStatus;
import org.example.vasservice.repositories.AccountRepository;
import org.example.vasservice.repositories.TransactionHistoryRepository;
import org.example.vasservice.services.AccountService;
import org.json.JSONObject;
import org.example.vasservice.entities.TransactionHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {

  @Autowired
  private TransactionHistoryRepository transactionHistoryRepository;

  private static final Logger log = LoggerFactory.getLogger(StartupListener.class);

  @Autowired
  private AccountService accountService;

  @KafkaListener(topics = "transaction", groupId = "vas")
  public void listen(String message) {
    try {
      // Chuyển đổi message JSON thành đối tượng TransactionHistory
      JSONObject json = new JSONObject(message);

      TransactionHistory transaction = new TransactionHistory();
      transaction.setTransactionId(UUID.fromString(json.getString("transactionId")));
      transaction.setSourceAccount(json.getString("sourceAccount"));
      transaction.setDestinationAccount(json.getString("destinationAccount"));
      transaction.setAmount(json.getBigDecimal("amount"));
      transaction.setNewBalance(json.getBigDecimal("newBalance"));
      transaction.setStatus(TransactionStatus.valueOf(json.getString("status")));
      transaction.setCreatedAt(LocalDateTime.parse(json.getString("createdAt")));
      transaction.setPaymentInfo(json.getString("paymentInfo"));

      accountService.updateAccountBalance(transaction.getSourceAccount(),
          transaction.getNewBalance());

      // Lưu vào database
      transactionHistoryRepository.save(transaction);

      log.info("Received Message in group - vas: " + transaction);

    } catch (Exception e) {
      log.error("Error processing Kafka message: " + e.getMessage());
    }
//    log.info("Received Message in group - vas: " + message);
  }

}