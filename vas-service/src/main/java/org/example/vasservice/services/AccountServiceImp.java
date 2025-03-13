package org.example.vasservice.services;

import java.util.UUID;
import org.example.vasservice.entities.Account;
import org.example.vasservice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public Account findByProfileId(UUID id) {
    return accountRepository.findByProfileId(id);
  }
}
