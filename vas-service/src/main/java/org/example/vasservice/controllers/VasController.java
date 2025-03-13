package org.example.vasservice.controllers;

import java.math.BigDecimal;
import java.util.UUID;
import org.example.vasservice.dto.PaymentInfo;
import org.example.vasservice.dto.PaymentRequest;
import org.example.vasservice.entities.Account;
import org.example.vasservice.idaAccount.MomoAccount;
import org.example.vasservice.services.AccountService;
import org.example.vasservice.services.PaymentClient;
import org.springframework.beans.factory.annotation.Autowired;
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
  public ResponseEntity<String> payment(@AuthenticationPrincipal Jwt jwt, @RequestParam("amount") BigDecimal amount) {
    String profileId = jwt.getClaim("profileId");
    Account account = accountService.findByProfileId(UUID.fromString(profileId));
    PaymentRequest paymentRequestSrc = new PaymentRequest(account.getAccountNumber(), amount);
    paymentClient.debit(paymentRequestSrc);
    PaymentRequest paymentRequestDest = new PaymentRequest(momoAccount.getAccountNo(), amount);

    paymentClient.credit(paymentRequestDest);

    return ResponseEntity.ok("Payment successful");
  }
}
