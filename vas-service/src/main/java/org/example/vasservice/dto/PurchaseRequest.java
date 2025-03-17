package org.example.vasservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseRequest {
  private String sourceAccountNo;
  private String destinationAccountNo;
  private Long productId;

}