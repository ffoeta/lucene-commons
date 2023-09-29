package com.example.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.kafka.config.KafkaCommonConfig.kafkaLogger;

@FunctionalInterface
public interface KafkaListenStrategy {
  Logger logger = LoggerFactory.getLogger(kafkaLogger);

  void onMessage(Long id);
}
