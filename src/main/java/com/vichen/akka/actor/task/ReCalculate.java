package com.vichen.akka.actor.task;

/**
 * @author vichen
 */
public class ReCalculate extends Calculate {

  /**
   * 重算类型
   */

  private String taskName;

  public ReCalculate(String planeUniqueCode, Long start, Long end, String flightId,
    String taskName) {
    super(planeUniqueCode, start, end, flightId);
    this.taskName = taskName;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }
}
