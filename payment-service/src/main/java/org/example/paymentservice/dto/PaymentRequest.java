package org.example.paymentservice.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PaymentRequest {
  private String accountNo;
  private BigDecimal amount;
  private Integer version;

}
