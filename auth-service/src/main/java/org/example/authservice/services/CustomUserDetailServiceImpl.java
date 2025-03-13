package org.example.authservice.services;

import java.util.UUID;
import org.example.authservice.entities.Profile;
import org.example.authservice.entities.UserEntity;
import org.example.authservice.repositories.ProfileRepository;
import org.example.authservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ProfileRepository profileRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return User.withDefaultPasswordEncoder()
        .username(userEntity.getUsername())
        .password(userEntity.getPassword()) // Password đã mã hóa
        .roles(userEntity.getRole()) // Vai trò
        .build();
  }

  public UUID getProfileIdByUsername(String username) {
    Profile p = profileRepository.findByUsername(username);
    return p.getProfileId();
  }



}

