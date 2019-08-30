package com.vichen.akka.actor.task;

/**
 * @author vichen
 */
public class Calculate {

  /**
   * 飞机尾号
   */
  private String planeUniqueCode;
  /**
   * 数据开始时间
   */
  private Long start;
  /**
   * 数据结束时间
   */
  private Long end;
  /**
   * 航班id
   */
  private String flightId;

  public Calculate(String planeUniqueCode, Long start, Long end, String flightId) {
    this.planeUniqueCode = planeUniqueCode;
    this.start = start;
    this.end = end;
    this.flightId = flightId;
  }

  public String getPlaneUniqueCode() {
    return planeUniqueCode;
  }

  public void setPlaneUniqueCode(String planeUniqueCode) {
    this.planeUniqueCode = planeUniqueCode;
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

  public String getFlightId() {
    return flightId;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }
}
