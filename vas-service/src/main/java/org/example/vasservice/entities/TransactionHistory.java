package org.example.vasservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
@Table(name = "transaction_history")
public class TransactionHistory extends Auditable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private UUID transactionId; // Mã giao dịch (UUID)

  private String sourceAccount; // Tài khoản trừ tiền

  private String destinationAccount; // Tài khoản nhận tiền

  private BigDecimal amount; // Số tiền giao dịch

  private BigDecimal newBalance; // Số tiền giao dịch

  @Enumerated(EnumType.STRING)
  private TransactionStatus status; // Trạng thái giao dịch

  private String paymentInfo;
}
