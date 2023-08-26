package com.example.kafka.config;

import com.example.kafka.model.KafkaMessage;
import com.example.kafka.settings.KafkaSettings;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import com.example.kafka.producer.KafkaProducer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerConfig {

  private final KafkaSettings kafkaSettings;

  public KafkaProducerConfig(KafkaSettings kafkaSettings) {
    this.kafkaSettings = kafkaSettings;
  }

  @Bean
  public ProducerFactory<String, KafkaMessage> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();

    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaSettings.getBootstrapAddress());
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaProducer kafkaProducer(ProducerFactory<String, KafkaMessage> producerFactory) {
    return new KafkaProducer(new KafkaTemplate<>(producerFactory));
  }
}
