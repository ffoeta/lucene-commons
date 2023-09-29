package com.example.kafka.listener;

import com.example.kafka.wrappers.KafkaConsumerFactoryWrapper;
import com.example.kafka.wrappers.KafkaConsumerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.example.kafka.config.KafkaCommonConfig.kafkaLogger;

public class KafkaListener {
  private final Logger logger = LoggerFactory.getLogger(kafkaLogger);
  private final String topic;
  private final KafkaConsumerWrapper consumer;
  private final KafkaListenStrategy listenStrategy;
  private KafkaListener(String topic, KafkaConsumerWrapper consumer, KafkaListenStrategy listenStrategy) {
    this.topic = topic;
    this.consumer = consumer;
    this.listenStrategy = listenStrategy;
  }

  @PostConstruct
  void subscribe() {
    consumer.subscribe(topic);
  }

  public void poll() {
    onMessages(consumer.poll());
  }

  private void onMessages(List<Long> messages) {
    messages.forEach(listenStrategy::onMessage);
  }

  public static class Builder {
    private String topic;
    private KafkaConsumerWrapper consumer;
    private KafkaListenStrategy listenStrategy;

    public static Builder factory(KafkaConsumerFactoryWrapper factory) {
      var instance = new Builder();
      instance.consumer = factory.getConsumer();
      return instance;
    }
    public Builder topic(String topic) {
      this.topic = topic;
      return this;
    }

    public KafkaListener listen(KafkaListenStrategy listenStrategy) {
      this.listenStrategy = listenStrategy;
      return new KafkaListener(this.topic, this.consumer, listenStrategy);
    }
  }
}
