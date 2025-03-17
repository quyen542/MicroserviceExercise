package org.example.vasservice.services;

import java.math.BigDecimal;
import java.util.UUID;
import org.example.vasservice.entities.Account;

public interface AccountService {

  Account findByProfileId(UUID id);

  void updateAccountBalance(String accountNo, BigDecimal balance);

}
