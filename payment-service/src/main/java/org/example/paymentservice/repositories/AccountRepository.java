package org.example.paymentservice.repositories;

import jakarta.persistence.LockModeType;
import org.example.paymentservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  Account findByAccountNumber(String accountNumber);

}
