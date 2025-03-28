package org.example.authservice.dto;

import java.util.Collection;
import java.util.List;
import org.example.authservice.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetail implements UserDetails {

  private UserEntity userEntity;

  public CustomUserDetail(UserEntity userEntity) {
    this.userEntity = userEntity;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> userEntity.getRole());
  }

  @Override
  public String getPassword() {
    return userEntity.getPassword();
  }

  @Override
  public String getUsername() {
    return userEntity.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

