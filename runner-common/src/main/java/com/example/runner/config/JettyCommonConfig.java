package com.example.runner.config;

import com.example.runner.jetty.JettyServerBuilder;
import com.example.runner.settings.JettySettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.net.InetAddress.getLocalHost;

@Configuration
public class JettyCommonConfig {
  public static final String jettyLogger = "jettyLogger";
  private static final Logger logger = LoggerFactory.getLogger(jettyLogger);

  private final JettySettings jettySettings;

  public JettyCommonConfig(JettySettings jettySettings) {
    this.jettySettings = jettySettings;
  }

  @Bean
  public JettyServerBuilder.JettyServer jettyServer() throws Exception {
    var jetty = JettyServerBuilder
        .jettyServerBuilder(jettySettings)
        .build();
    jetty.start();
    logger.info("Started jetty server at {}", getLocalHost());
    logger.info("-------------------------------------------------------");
    return jetty;
  }
}
