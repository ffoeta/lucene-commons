package com.example.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public class KafkaProducer {
  private final KafkaTemplate<String, String> template;

  public KafkaProducer(KafkaTemplate<String, String> template) {
    this.template = template;
  }

  public CompletableFuture<SendResult<String, String>> send(String topic, String value) {
    return this.template.send(topic, value).completable();
  }
}
