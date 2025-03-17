package org.example.paymentservice.services;

import java.math.BigDecimal;

public interface AccountService {

  boolean debit(String accountNumber, BigDecimal amount);

  boolean credit(String accountNumber, BigDecimal amount);

  void purchase(String sourceAccountNumber, String destinationAccountNo, Long productId);


}
