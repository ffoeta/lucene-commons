package com.example.kafka.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class KafkaSettings {
  @Value("${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  @Value("${kafka.groupId}")
  private String groupId;

  @Value("${kafka.pollInterval}")
  private Long pollInterval;

  public String getBootstrapAddress() {
    return bootstrapAddress;
  }

  public void setBootstrapAddress(String bootstrapAddress) {
    this.bootstrapAddress = bootstrapAddress;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public Long getPollInterval() {
    return pollInterval;
  }

  public void setPollInterval(Long pollInterval) {
    this.pollInterval = pollInterval;
  }
}
