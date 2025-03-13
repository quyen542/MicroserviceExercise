package org.example.vasservice.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {
  private static final Logger log = LoggerFactory.getLogger(StartupListener.class);

  @KafkaListener(topics = "transaction", groupId = "vas")
  public void listen(String message) {
    log.info("Received Message in group - vas: " + message);
  }

}