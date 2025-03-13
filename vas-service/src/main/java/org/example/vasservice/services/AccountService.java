package org.example.vasservice.services;

import java.util.UUID;
import org.example.vasservice.entities.Account;

public interface AccountService {

  Account findByProfileId(UUID id);

}
