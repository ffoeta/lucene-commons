package com.example.kafka.listener;

import com.example.kafka.config.wrappers.KafkaConsumerFactoryWrapper;
import com.example.kafka.config.wrappers.KafkaConsumerWrapper;
import com.example.kafka.model.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.List;

public abstract class KafkaListener<T extends KafkaMessage> {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListener.class);
  private final String topic;
  private final KafkaConsumerWrapper consumerWrapper;
  protected KafkaListener(String topic, KafkaConsumerFactoryWrapper kafkaConsumerFactoryWrapper) {
    this.topic = topic;
    this.consumerWrapper = kafkaConsumerFactoryWrapper.getConsumer();
  }

  @PostConstruct
  void subscribe() {
    consumerWrapper.subscribe(topic);
  }

  public void poll() {
    onMessages(consumerWrapper.poll());
  }

  private void onMessages(List<T> messages) {
    messages.forEach(this::onMessage);
  }

  protected abstract <T> void onMessage(T message);
}
