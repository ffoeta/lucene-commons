package com.example.kafka.wrappers;

import org.springframework.kafka.core.ConsumerFactory;

public class KafkaConsumerFactoryWrapper {
  private final ConsumerFactory<String, Long> factory;
  public KafkaConsumerFactoryWrapper(ConsumerFactory<String, Long> factory) {
    this.factory = factory;
  }

  public KafkaConsumerWrapper getConsumer() {
    return new KafkaConsumerWrapper(factory.createConsumer());
  }
}
