package org.example.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    // @formatter:off
    http
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests.anyRequest().authenticated()
        )
//        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())) // Kích hoạt xác thực JWT
        .formLogin(Customizer.withDefaults());
    // @formatter:on

    return http.build();
  }
}

