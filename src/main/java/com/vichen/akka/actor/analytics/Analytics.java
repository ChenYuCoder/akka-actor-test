package com.vichen.akka.actor.analytics;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vichen
 */
@Document(collection="SpringAir") public class Analytics {
  @Id private String id;

  private JSONObject data;
  private String devId;
  private String origin;
  private String sId;
  private String type;
  private String uid;
  private String version;
  private String planeUniqueNo;
  private Long receiveTime;
  private Long cs;
  private Long ts;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public JSONObject getData() {
    return data;
  }

  public void setData(JSONObject data) {
    this.data = data;
  }

  public String getDevId() {
    return devId;
  }

  public void setDevId(String devId) {
    this.devId = devId;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getsId() {
    return sId;
  }

  public void setsId(String sId) {
    this.sId = sId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getPlaneUniqueNo() {
    return planeUniqueNo;
  }

  public void setPlaneUniqueNo(String planeUniqueNo) {
    this.planeUniqueNo = planeUniqueNo;
  }

  public Long getReceiveTime() {
    return receiveTime;
  }

  public void setReceiveTime(Long receiveTime) {
    this.receiveTime = receiveTime;
  }

  public Long getCs() {
    return cs;
  }

  public void setCs(Long cs) {
    this.cs = cs;
  }

  public Long getTs() {
    return ts;
  }

  public void setTs(Long ts) {
    this.ts = ts;
  }
}
