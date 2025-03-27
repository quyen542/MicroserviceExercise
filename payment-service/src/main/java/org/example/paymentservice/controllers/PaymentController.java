package org.example.paymentservice.controllers;


import java.util.UUID;
import org.example.paymentservice.dto.PurchaseRequest;
import org.example.paymentservice.entities.PaymentTransaction;
import org.example.paymentservice.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  @Autowired
  private PurchaseService purchaseService;

  @PostMapping("/purchase")
  public ResponseEntity<UUID> purchase(@RequestBody PurchaseRequest request) {
    PaymentTransaction transaction =  purchaseService.purchase(request.getSourceAccountNo(), request.getDestinationAccountNo(), request.getProductId(), request.getVersion());
    return ResponseEntity.ok(transaction.getTransactionId());
  }


}
