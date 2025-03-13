package org.example.authservice.controllers;

import org.example.authservice.dto.LoginDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class LoginController {

  Logger logger = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @PostMapping
  public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
    //Authenticate
    logger.info(loginDTO.getPassword() + " " + loginDTO.getUsername());
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
    final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
    return new ResponseEntity<>(HttpStatus.OK);
  }


}
