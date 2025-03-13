package org.example.authservice.repositories;

import org.example.authservice.entities.Profile;
import org.example.authservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository  extends JpaRepository<Profile, Long> {

  @Query("select p from Profile p, UserEntity u where p.userEntity.id = u.id and u.username = ?1 ")
  Profile findByUsername(String username);

}
