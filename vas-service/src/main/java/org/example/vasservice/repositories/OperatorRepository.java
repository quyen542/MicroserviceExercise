package org.example.vasservice.repositories;

import java.util.Optional;
import org.example.vasservice.entities.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {

  Optional<Operator> findById(Long id);

  Optional<Operator> findByName(String name);
}
