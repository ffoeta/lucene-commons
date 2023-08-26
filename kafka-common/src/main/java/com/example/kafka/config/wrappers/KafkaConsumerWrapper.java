package com.example.kafka.config.wrappers;

import com.example.kafka.model.KafkaMessage;
import org.apache.kafka.clients.consumer.Consumer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class KafkaConsumerWrapper<T extends KafkaMessage> {
  private static final Duration POOL_TIME = Duration.ofMillis(100);
  Consumer<String, T> consumer;

  public KafkaConsumerWrapper(Consumer<String, T> consumer) {
    this.consumer = consumer;
  }

  public void subscribe(String topic) {
    consumer.subscribe(List.of(topic));
  }

  public void unsubscribe() {
    consumer.unsubscribe();
  }

  public List<T> poll() {
    List<T> messages = new ArrayList<>();
    for (var record : consumer.poll(POOL_TIME)) {
      messages.add(record.value());
    }
    return messages;
  }
}
