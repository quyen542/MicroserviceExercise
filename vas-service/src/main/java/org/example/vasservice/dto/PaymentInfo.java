package org.example.vasservice.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentInfo {
  private String accountSrc;
  private String accountDest;
  private BigDecimal amount;
}
