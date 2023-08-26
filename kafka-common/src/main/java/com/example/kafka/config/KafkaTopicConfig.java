package com.example.kafka.config;

import com.example.kafka.settings.KafkaSettings;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

public class KafkaTopicConfig {

  private KafkaSettings kafkaSettings;

  public KafkaTopicConfig(KafkaSettings kafkaSettings) {
    this.kafkaSettings = kafkaSettings;
  }

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaSettings.getBootstrapAddress());
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic book() {
    return TopicBuilder.name("BOOK")
        .build();
  }

  @Bean
  public NewTopic other() {
    return TopicBuilder.name("AUTHOR")
        .build();
  }
}
