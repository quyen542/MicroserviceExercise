package org.example.paymentservice.controllers;


import org.example.paymentservice.dto.PaymentRequest;
import org.example.paymentservice.dto.PurchaseRequest;
import org.example.paymentservice.services.AccountService;
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
  private AccountService accountService;

  @PostMapping("/credit")
  public ResponseEntity<String> credit(@RequestBody PaymentRequest request) {
    boolean check = accountService.credit(request.getAccountNo(), request.getAmount());
    return ResponseEntity.ok("Credit transaction successful!");
  }

  @PostMapping("/debit")
  public ResponseEntity<String> debit(@RequestBody PaymentRequest request) {
    boolean check = accountService.debit(request.getAccountNo(), request.getAmount());
    return ResponseEntity.ok("Debit transaction successful!");
  }

  @PostMapping("/purchase")
  public ResponseEntity<String> purchase(@RequestBody PurchaseRequest request) {
    accountService.purchase(request.getSourceAccountNo(), request.getDestinationAccountNo(), request.getProductId());
    return ResponseEntity.ok("purchase transaction successful!");
  }


}
