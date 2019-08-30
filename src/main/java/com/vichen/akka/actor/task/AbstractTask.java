package com.vichen.akka.actor.task;

import akka.actor.AbstractActor;
import com.alibaba.fastjson.JSON;
import com.vichen.akka.actor.task.finish.TaskFinishData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vichen
 */
public class AbstractTask extends AbstractActor {

  protected String taskId;

  protected AbstractTask(String taskId) {
    this.taskId = taskId;
  }

  protected Logger logger = LoggerFactory.getLogger(getClass());

  protected long start = 0L;
  protected long end = 0L;
  protected long dataSize = 0L;
  protected long resultSize = 0L;
  protected Calculate calculate;
  protected boolean success = false;
  protected boolean rootTask = false;


  /**
   * 任务初始化
   */
  @Override public void preStart() {
    start = System.currentTimeMillis();
    taskFinish();
    logger.debug(getTaskName() + " preStart");
  }

  /**
   * 监听消息
   * @return
   */
  @Override public Receive createReceive() {
    return receiveBuilder().matchAny(m -> {
      doTask((Calculate) m);
      getContext().stop(getSelf());
    }).build();
  }

  /**
   * 执行任务
   * 标记任务条件
   * 标记原数据大小
   * 标记结果数据大小
   * 标记执行结果
   * 标记是否为根任务
   * <p>
   * catch 异常 发送 任务异常消息
   *
   * @param m
   */

  protected void doTask(Calculate m) {
    //标记原数据量级
    this.calculate = m;
    dataSize = 1L;
    logger.debug("{}:do task,data:{}", getTaskName(), JSON.toJSONString(m));
    //标记产出数量级
    resultSize = 1L;
    //标记任务执行结果
    success = true;
    rootTask = false;
  }

  /**
   * 获取任务名称
   * @return
   */
  public String getTaskName() {
    return "abstractTAsk";
  }

  /**
   * 没有handle的消息
   * @param message 消息内容
   */
  @Override public void unhandled(final Object message) {
    logger.warn(JSON.toJSONString(message));
  }

  private void taskFinish(){
    TaskFactory.getTaskFinishListener().tell(
      new TaskFinishData(taskId, calculate.getPlaneUniqueCode(), getTaskName(), dataSize,
        resultSize, start, end, success, calculate, rootTask), getSelf());
  }

  /**
   * 任务销毁
   */
  @Override public void postStop() {
    end = System.currentTimeMillis();
    taskFinish();
    logger.debug(getTaskName() + " post stop");

  }



}
