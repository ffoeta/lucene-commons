package com.example.kafka.config;

import com.example.kafka.listener.KafkaListener;
import com.example.kafka.settings.KafkaSettings;
import com.example.runner.util.StoppableThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KafkaListenersContainer extends StoppableThread {
  private static final Logger logger = LoggerFactory.getLogger(KafkaListenersContainer.class);
  private final Long poolInterval;

  List<KafkaListener> kafkaListenerList = new ArrayList<>();
  public KafkaListenersContainer(KafkaSettings kafkaSettings, KafkaListener<?> ... kafkaListeners) {
    kafkaListenerList.addAll(Arrays.asList(kafkaListeners));
    this.poolInterval = kafkaSettings.getPollInterval();
  }

  @Override
  public void run() {
    while (!stopNow) {
      kafkaListenerList.forEach(KafkaListener::poll);
      try {
        sleep(poolInterval);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @PostConstruct
  public void info() {
    logger.info("Got {} beans of type KafkaListener", kafkaListenerList.size());
  }
}
