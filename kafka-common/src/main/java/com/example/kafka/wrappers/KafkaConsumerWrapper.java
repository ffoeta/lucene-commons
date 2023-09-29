package com.example.kafka.wrappers;

import org.apache.kafka.clients.consumer.Consumer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class KafkaConsumerWrapper{
  private static final Duration POOL_TIME = Duration.ofMillis(100);
  Consumer<String, Long> consumer;

  public KafkaConsumerWrapper(Consumer<String, Long> consumer) {
    this.consumer = consumer;
  }

  public void subscribe(String topic) {
    consumer.subscribe(List.of(topic));
  }

  public void unsubscribe() {
    consumer.unsubscribe();
  }

  public List<Long> poll() {
    List<Long> messages = new ArrayList<>();
    for (var record : consumer.poll(POOL_TIME)) {
      messages.add(record.value());
    }
    return messages;
  }
}
