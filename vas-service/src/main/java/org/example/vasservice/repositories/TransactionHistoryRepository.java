package org.example.vasservice.repositories;

import org.example.vasservice.entities.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

}
