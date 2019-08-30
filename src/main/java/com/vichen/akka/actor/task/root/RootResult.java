package com.vichen.akka.actor.task.root;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vichen
 */
@Document public class RootResult {
  @Id private String id;
  private int count;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public void addCount(int click) {
    count += click;
  }
}
