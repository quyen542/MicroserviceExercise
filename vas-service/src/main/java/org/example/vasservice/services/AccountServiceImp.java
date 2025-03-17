package org.example.vasservice.services;

import java.math.BigDecimal;
import java.util.UUID;
import org.example.vasservice.entities.Account;
import org.example.vasservice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImp implements AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  @Transactional
  public Account findByProfileId(UUID id) {
    return accountRepository.findByProfileId(id);
  }

  @Override
  @Transactional
  public void updateAccountBalance(String accountNo, BigDecimal balance) {
    Account account = accountRepository.findByAccountNumber(accountNo)
        .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    account.setBalance(balance);
    accountRepository.save(account);
  }
}
