package com.vichen.akka.actor.task.finish;

import com.vichen.akka.actor.task.Calculate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * @author vichen
 */
@Document public class TaskFinishData {
  @Id private String id;
  private String taskId;
  private String planeUniqueNo;
  private String taskName;
  private Long dataSize;
  private Long resultSize;
  private Long start;
  private Long end;
  private Calculate calculate;

  private boolean rootTask;
  private boolean success;

  public TaskFinishData(@NotNull String taskId, @NotNull String planeUniqueNo,
    @NotNull String taskName, @NotNull Long dataSize, Long resultSize, @NotNull Long start,
    @NotNull Long end, @NotNull boolean success, @NotNull Calculate calculate,
    @NotNull boolean rootTask) {
    this.id = taskId + "_" + taskName;
    this.taskId = taskId;
    this.planeUniqueNo = planeUniqueNo;
    this.taskName = taskName;
    this.dataSize = dataSize;
    this.resultSize = resultSize;
    this.start = start;
    this.end = end;
    this.success = success;
    this.calculate = calculate;
    this.rootTask = rootTask;
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

  public Long getDataSize() {
    return dataSize;
  }

  public void setDataSize(Long dataSize) {
    this.dataSize = dataSize;
  }

  public Long getResultSize() {
    return resultSize;
  }

  public void setResultSize(Long resultSize) {
    this.resultSize = resultSize;
  }

  public Long getStart() {
    return start;
  }

  public void setStart(Long start) {
    this.start = start;
  }

  public Long getEnd() {
    return end;
  }

  public void setEnd(Long end) {
    this.end = end;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public Calculate getCalculate() {
    return calculate;
  }

  public void setCalculate(Calculate calculate) {
    this.calculate = calculate;
  }

  public boolean isRootTask() {
    return rootTask;
  }

  public void setRootTask(boolean rootTask) {
    this.rootTask = rootTask;
  }
}
