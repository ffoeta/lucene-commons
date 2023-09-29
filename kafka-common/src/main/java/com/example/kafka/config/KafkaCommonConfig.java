package com.example.kafka.config;

import com.example.kafka.settings.KafkaSettings;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    KafkaSettings.class,
    KafkaProducerConfig.class,
    KafkaConsumerConfig.class
})
@Configuration
public class KafkaCommonConfig {
  public static final String kafkaLogger = "kafkaLogger";
}