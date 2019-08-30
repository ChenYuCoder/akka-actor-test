package com.vichen.akka.actor.task.index;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document public class TaskIndex {
  @Id private String id;
  private String taskName;
  private String planeUniqueNo;
  private String taskId;
  private long finishTime;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public TaskIndex(String taskName, String planeUniqueNo, String taskId, long finishTime) {
    this.id = taskName + "_" + planeUniqueNo;
    this.taskName = taskName;
    this.planeUniqueNo = planeUniqueNo;
    this.taskId = taskId;
    this.finishTime = finishTime;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getPlaneUniqueNo() {
    return planeUniqueNo;
  }

  public void setPlaneUniqueNo(String planeUniqueNo) {
    this.planeUniqueNo = planeUniqueNo;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public long getFinishTime() {
    return finishTime;
  }

  public void setFinishTime(long finishTime) {
    this.finishTime = finishTime;
  }
}
