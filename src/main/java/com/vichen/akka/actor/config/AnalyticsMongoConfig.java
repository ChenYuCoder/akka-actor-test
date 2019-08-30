package com.vichen.akka.actor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author vichen
 */
@Configuration @EnableMongoRepositories(basePackages = {
  "com.vichen.akka.actor.analytics"}, mongoTemplateRef = "analyticsMongoTemplate")
@ConfigurationProperties(prefix = "mongodb.analytics") public class AnalyticsMongoConfig
  extends AbstractMongoConfigure {
  @Override @Bean(name = "analyticsMongoTemplate") public MongoTemplate getMongoTemplate()
    throws Exception {
    return new MongoTemplate(mongoDbFactory());
  }
}
