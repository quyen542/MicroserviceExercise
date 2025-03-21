package org.example.vasservice.controllers;

import feign.FeignException;
import jakarta.persistence.LockModeType;
import jakarta.persistence.OptimisticLockException;
import java.math.BigDecimal;
import java.util.UUID;
import org.example.vasservice.dto.PaymentInfo;
import org.example.vasservice.dto.PaymentRequest;
import org.example.vasservice.dto.PurchaseRequest;
import org.example.vasservice.entities.Account;
import org.example.vasservice.entities.TransactionHistory;
import org.example.vasservice.idaAccount.MomoAccount;
import org.example.vasservice.services.AccountService;
import org.example.vasservice.services.PaymentClient;
import org.example.vasservice.services.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vas")
public class VasController {

  @Autowired
  private PaymentClient paymentClient;

  @Autowired
  private AccountService accountService;

  @Autowired
  private MomoAccount momoAccount;

  @Autowired
  private TransactionHistoryService transactionHistoryService;



  @GetMapping("/profile")
  public String getProfile(@AuthenticationPrincipal Jwt jwt) {
    String profileId = jwt.getClaim("profileId"); // Lấy profileId từ JWT claims
    return "Authenticated user with profileId: " + profileId;
  }

  @PostMapping("/payment")
  public ResponseEntity<TransactionHistory> payment(@AuthenticationPrincipal Jwt jwt,
      @RequestParam("productId") Long productId, @RequestParam("version") Integer version) {
    String profileId = jwt.getClaim("profileId");
    Account account = accountService.findByProfileId(UUID.fromString(profileId));

    if (account == null) {
      throw new IllegalArgumentException("Account not found");
    }

    if (!account.getVersion().equals(version)) {
      throw new OptimisticLockException("Data is changed");
    }

    UUID transactionId;
    try{
      transactionId = paymentClient.purchase(PurchaseRequest.builder().productId(productId).sourceAccountNo(
          account.getAccountNumber()).destinationAccountNo(momoAccount.getAccountNo()).version(version).build());
    }catch (FeignException e) {
      throw new RuntimeException("Payment failed: " + e.contentUTF8());
    }

    int maxRetries = 10;
    int retryDelayMillis = 300; // 300ms x 10 = 3s max wait
    TransactionHistory history = null;

    for (int i = 0; i < maxRetries; i++) {
      history = transactionHistoryService.findByTxId(transactionId);
      if (history != null) break;
      try {
        Thread.sleep(retryDelayMillis);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Retry interrupted", e);
      }
    }


    if (history == null) {
      throw new IllegalArgumentException("Transaction not found");
    }

    return ResponseEntity.ok(history);
  }
}
