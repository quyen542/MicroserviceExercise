package org.example.vasservice.config;

import java.util.UUID;
import org.example.vasservice.entities.AccountType;
import org.example.vasservice.idaAccount.MomoAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdaAccountConfig {
  @Bean
  public MomoAccount momoAccount() {
    return new MomoAccount(AccountType.IDA, "0909999999", 1000000L, UUID.fromString("77af9623-ac3d-4893-ab0d-684084068723"));
  }
}
