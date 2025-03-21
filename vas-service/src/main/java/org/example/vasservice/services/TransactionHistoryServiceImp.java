package org.example.vasservice.services;

import java.util.UUID;
import org.example.vasservice.entities.TransactionHistory;
import org.example.vasservice.repositories.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionHistoryServiceImp implements TransactionHistoryService {

  @Autowired
  private TransactionHistoryRepository repo;

  @Override
  public TransactionHistory findByTxId(UUID txId) {
    return repo.findTransactionHistoriesByTransactionId(txId);
  }
}
