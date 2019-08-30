package com.vichen.akka.actor.config;

import com.mongodb.MongoClientURI;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @author vichen
 */
public abstract class AbstractMongoConfigure {

  private String url;

  public MongoDbFactory mongoDbFactory() throws Exception {

    return new SimpleMongoDbFactory(new MongoClientURI(url));
  }

  abstract public MongoTemplate getMongoTemplate() throws Exception;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
