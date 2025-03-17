package org.example.paymentservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
@Table(name = "account")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private UUID profileId;

  private String accountNumber;

  @Enumerated(EnumType.STRING)
  private AccountType accountType;

  private BigDecimal balance;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
  List<PaymentTransaction> transactions;



}
