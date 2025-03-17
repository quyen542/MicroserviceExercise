package org.example.paymentservice.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PurchaseRequest {
  private String sourceAccountNo;
  private String destinationAccountNo;
  private Long productId;
}

