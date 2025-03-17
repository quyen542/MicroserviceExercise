package org.example.vasservice.controllers;

import jakarta.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.UUID;
import org.example.vasservice.dto.PaymentInfo;
import org.example.vasservice.dto.PaymentRequest;
import org.example.vasservice.dto.PurchaseRequest;
import org.example.vasservice.entities.Account;
import org.example.vasservice.idaAccount.MomoAccount;
import org.example.vasservice.services.AccountService;
import org.example.vasservice.services.PaymentClient;
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


  @GetMapping("/profile")
  public String getProfile(@AuthenticationPrincipal Jwt jwt) {
    String profileId = jwt.getClaim("profileId"); // Lấy profileId từ JWT claims
    return "Authenticated user with profileId: " + profileId;
  }

  @PostMapping("/payment")
  public ResponseEntity<String> payment(@AuthenticationPrincipal Jwt jwt,
      @RequestParam("productId") Long productId) {
    String profileId = jwt.getClaim("profileId");
    Account account = accountService.findByProfileId(UUID.fromString(profileId));
    if (account == null) {
      return ResponseEntity.ofNullable("Not found account with profileId: " + profileId);
    }

    paymentClient.purchase(PurchaseRequest.builder().productId(productId).sourceAccountNo(
        account.getAccountNumber()).destinationAccountNo(momoAccount.getAccountNo()).build());
    return ResponseEntity.ok("Payment successful");
  }
}
