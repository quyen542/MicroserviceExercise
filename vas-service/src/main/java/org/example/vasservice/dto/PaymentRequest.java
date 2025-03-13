package org.example.vasservice.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentRequest {
  private String accountNo;
  private BigDecimal amount;

  public PaymentRequest(String accountNo, BigDecimal amount) {
    this.accountNo = accountNo;
    this.amount = amount;
  }
}
