package com.example.kafka.config;


import com.example.kafka.settings.KafkaSettings;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;
import org.apache.kafka.common.serialization.StringDeserializer;


import java.util.HashMap;
import java.util.Map;

public class KafkaConsumerConfig {

  @Bean
  public ConsumerFactory<String, String> consumerFactory(KafkaSettings kafkaSettings) {
    Map<String, Object> props = new HashMap<>();

    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaSettings.getBootstrapAddress());
    props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaSettings.getGroupId());
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    return new DefaultKafkaConsumerFactory<>(props);
  }

  public static MessageListenerContainer createListener(ConsumerFactory<String, String> consumerFactory, String topic, Object messageListener) {
    ContainerProperties cProps = new ContainerProperties(topic);
    KafkaMessageListenerContainer<String, String> listenerContainer = new KafkaMessageListenerContainer<>(consumerFactory, cProps);
    listenerContainer.setupMessageListener(messageListener);
    return listenerContainer;
  }
}
