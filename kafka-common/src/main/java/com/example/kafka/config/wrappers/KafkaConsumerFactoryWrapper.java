package com.example.kafka.config.wrappers;

import com.example.kafka.model.KafkaMessage;
import org.springframework.kafka.core.ConsumerFactory;

public class KafkaConsumerFactoryWrapper {
  private final ConsumerFactory<String, KafkaMessage> factory;
  public KafkaConsumerFactoryWrapper(ConsumerFactory<String, KafkaMessage> factory) {
    this.factory = factory;
  }

  public KafkaConsumerWrapper getConsumer() {
    return new KafkaConsumerWrapper(factory.createConsumer());
  }
}
