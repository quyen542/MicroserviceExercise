package org.example.paymentservice.services;

import java.math.BigDecimal;
import org.example.paymentservice.entities.Account;
import org.example.paymentservice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

  @Autowired
  private AccountRepository accountRepo;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String msg) {
    kafkaTemplate.send("transaction", msg);
  }


  @Override
  public boolean debit(String accountNumber, BigDecimal amount) {
    Account a = accountRepo.findByAccountNumber(accountNumber);
    a.setBalance(a.getBalance().subtract(amount));
    accountRepo.save(a);
    sendMessage("Account Number: " + accountNumber + ", Amount: -" + amount + ", Status: Success" + ", Type: Debit");

    return true;
  }

  @Override
  public boolean credit(String accountNumber, BigDecimal amount) {
    Account a = accountRepo.findByAccountNumber(accountNumber);
    a.setBalance(a.getBalance().add(amount));
    accountRepo.save(a);
    sendMessage("Account Number: " + accountNumber + ", Amount: +" + amount + ", Status: Success" + ", Type: credit");
    return true;
  }
}
