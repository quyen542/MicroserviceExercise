package org.example.vasservice.idaAccount;

import java.util.UUID;
import lombok.Data;
import org.example.vasservice.entities.AccountType;

@Data
public class MomoAccount {
  private final AccountType type;
  private final String accountNo;
  private final Long balance;
  private final UUID profileId;

}

