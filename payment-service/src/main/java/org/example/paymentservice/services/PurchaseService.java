package org.example.paymentservice.services;

import java.math.BigDecimal;
import java.util.UUID;
import org.example.paymentservice.entities.PaymentTransaction;

public interface PurchaseService {

  boolean debit(String accountNumber, BigDecimal amount, Integer version);

  boolean credit(String accountNumber, BigDecimal amount, Integer version);

  PaymentTransaction purchase(String sourceAccountNumber, String destinationAccountNo, Long productId, Integer version);


}
