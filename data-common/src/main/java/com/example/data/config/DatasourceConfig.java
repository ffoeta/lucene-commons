package com.example.data.config;

import com.example.data.settings.DatasourceSettings;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

public class DatasourceConfig {

  @Bean
  public SessionFactory dataSource(DatasourceSettings datasourceSettings) {
    Configuration configuration = new Configuration();

    configuration.setProperty("connection.driver_class", datasourceSettings.getDriver());
    configuration.setProperty("dialect", datasourceSettings.getDialect());
    configuration.setProperty("hibernate.connection.url", datasourceSettings.getUrl());
    configuration.setProperty("hibernate.connection.username", datasourceSettings.getUser());
    configuration.setProperty("hibernate.connection.password", datasourceSettings.getPassword());
    configuration.setProperty("hibernate.current_session_context_class", "thread");
    configuration.setProperty("hibernate.show_sql", "true");
    configuration.setProperty("hibernate.format_sql", "true");


    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
    return configuration.buildSessionFactory(builder.build());
  }
}
