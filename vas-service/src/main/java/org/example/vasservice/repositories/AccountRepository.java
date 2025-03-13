package org.example.vasservice.repositories;

import java.util.UUID;
import org.example.vasservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Account findByProfileId(UUID profileId);
}
