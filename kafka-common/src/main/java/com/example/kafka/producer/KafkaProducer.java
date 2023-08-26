package com.example.kafka.producer;

import com.example.kafka.model.KafkaMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public class KafkaProducer {
  private final KafkaTemplate<String, KafkaMessage> template;

  public KafkaProducer(KafkaTemplate<String, KafkaMessage> template) {
    this.template = template;
  }

  public CompletableFuture<SendResult<String, KafkaMessage>> send(String topic, KafkaMessage kafkaMessage) {
    return this.template.send(topic, kafkaMessage).completable();
  }
}
