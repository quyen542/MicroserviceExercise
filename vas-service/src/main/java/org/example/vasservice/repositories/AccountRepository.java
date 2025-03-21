package org.example.vasservice.repositories;

import jakarta.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;
import org.example.vasservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Account findByProfileId(UUID profileId);
  Optional<Account> findByAccountNumber(String accountNumber);
}
