package org.example.authservice.config;

import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

@Configuration
public class ClientConfig {

  @Bean
  public RegisteredClientRepository registeredClientRepository() {
    // @formatter:off
    RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId("huongdanjava")
        .clientSecret("{noop}123456")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .redirectUri("https://oidcdebugger.com/debug")
        .scope("test")
        .build();
    // @formatter:on

    return new InMemoryRegisteredClientRepository(registeredClient);
  }

//  @Bean
//  public UserDetailsService users() {
//    UserDetails user = User.withDefaultPasswordEncoder()
//        .username("admin")
//        .password("password")
//        .roles("ADMIN")
//        .build();
//
//    return new InMemoryUserDetailsManager(user);
//  }
}

