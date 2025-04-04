package org.example.vasservice.services;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.vasservice.dto.PaymentRequest;
import org.example.vasservice.dto.PurchaseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://localhost:8086")
public interface PaymentClient {

  @PostMapping("/payments/purchase")
  UUID purchase(@RequestBody PurchaseRequest request);

}
