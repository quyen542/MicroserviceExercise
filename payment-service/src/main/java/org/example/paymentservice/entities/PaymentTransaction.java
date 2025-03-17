package org.example.paymentservice.entities;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "transactions")
@AllArgsConstructor
public class PaymentTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private UUID transactionId; // Mã giao dịch (UUID)

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account; // Tài khoản cong/trừ tiền

  private BigDecimal amount; // Số tiền giao dịch

  @Enumerated(EnumType.STRING)
  private TransactionType transactionType;

  @Enumerated(EnumType.STRING)
  private TransactionStatus status; // Trạng thái giao dịch


  private LocalDateTime createdAt;

  private String paymentInfo;


  public PaymentTransaction() {

  }
}
