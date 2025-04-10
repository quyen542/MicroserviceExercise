package org.example.vasservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class VasServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(VasServiceApplication.class, args);
  }


  @Bean
  public ModelMapper getModelMapper() {
    return new ModelMapper();
  }

}
