package org.example.paymentservice.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class TransactionMessage {

  private UUID transactionId; // Mã giao dịch (UUID)

  private String sourceAccount; // Tài khoản trừ tiền

  private String destinationAccount; // Tài khoản nhận tiền

  private BigDecimal amount; // Số tiền giao dịch

  private BigDecimal newBalance;

  @Enumerated(EnumType.STRING)
  private TransactionStatus status; // Trạng thái giao dịch

  private LocalDateTime createdAt;

  private String paymentInfo;

  public TransactionMessage(UUID transactionId, String sourceAccount, String destinationAccount,
      BigDecimal amount, BigDecimal newBalance, TransactionStatus status, LocalDateTime createdAt,
      String paymentInfo) {
    this.transactionId = transactionId;
    this.sourceAccount = sourceAccount;
    this.destinationAccount = destinationAccount;
    this.amount = amount;
    this.newBalance = newBalance;
    this.status = status;
    this.createdAt = createdAt;
    this.paymentInfo = paymentInfo;
  }
}
