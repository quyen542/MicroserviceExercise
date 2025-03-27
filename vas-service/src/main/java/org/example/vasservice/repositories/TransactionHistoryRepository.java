package org.example.vasservice.repositories;

import java.util.UUID;
import org.example.vasservice.entities.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

  TransactionHistory findTransactionHistoriesByTransactionId(UUID transactionId);

}
