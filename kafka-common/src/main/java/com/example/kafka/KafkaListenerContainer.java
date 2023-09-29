package com.example.kafka;

import com.example.kafka.listener.KafkaListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.example.kafka.config.KafkaCommonConfig.kafkaLogger;

@Component
public class KafkaListenerContainer {
  private final Logger logger = LoggerFactory.getLogger(kafkaLogger);

  public KafkaListenerContainer(KafkaListener... kafkaListeners) {
    logger.info(Arrays.toString(kafkaListeners));
  }
}
