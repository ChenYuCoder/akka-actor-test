package com.vichen.akka.actor.task.finish;

import com.vichen.akka.actor.task.Calculate;
import org.springframework.data.annotation.Id;

/**
 * @author vichen
 */
public class TaskExceptionData {

  @Id private String id;
  private String taskId;
  private String planeUniqueNo;
  private String taskName;
  private Calculate calculate;
  private String exceptionMessage;

  public TaskExceptionData(String taskId, String planeUniqueNo, String taskName,
    Calculate calculate, String exceptionMessage) {
    this.id = taskId + "_" + taskName;
    this.taskId = taskId;
    this.planeUniqueNo = planeUniqueNo;
    this.taskName = taskName;
    this.calculate = calculate;
    this.exceptionMessage = exceptionMessage;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getPlaneUniqueNo() {
    return planeUniqueNo;
  }

  public void setPlaneUniqueNo(String planeUniqueNo) {
    this.planeUniqueNo = planeUniqueNo;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public Calculate getCalculate() {
    return calculate;
  }

  public void setCalculate(Calculate calculate) {
    this.calculate = calculate;
  }

  public String getExceptionMessage() {
    return exceptionMessage;
  }

  public void setExceptionMessage(String exceptionMessage) {
    this.exceptionMessage = exceptionMessage;
  }
}
