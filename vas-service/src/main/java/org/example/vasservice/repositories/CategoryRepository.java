package org.example.vasservice.repositories;

import java.util.Optional;
import org.example.vasservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findById(Long id);

  Category findByName(String name);
}
