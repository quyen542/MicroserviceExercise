package org.example.vasservice.services;

import java.util.UUID;
import org.example.vasservice.entities.TransactionHistory;

public interface TransactionHistoryService {

  TransactionHistory findByTxId(UUID txId);

}
