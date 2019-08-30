package com.vichen.akka.actor.task.root;

import akka.actor.ActorRef;
import akka.actor.Props;
import com.vichen.SpringContextUtil;
import com.vichen.akka.actor.analytics.Analytics;
import com.vichen.akka.actor.analytics.AnalyticsRepository;
import com.vichen.akka.actor.task.AbstractTask;
import com.vichen.akka.actor.task.Calculate;
import com.vichen.akka.actor.task.TaskFactory;
import com.vichen.akka.actor.task.finish.TaskExceptionData;
import com.vichen.akka.actor.task.root.taskA.TaskA;
import com.vichen.akka.actor.task.root.taskB.TaskB;

import java.util.List;

/**
 * @author vichen
 */
public class RootTask extends AbstractTask {

  private final AnalyticsRepository analyticsRepository =
    SpringContextUtil.getBean(AnalyticsRepository.class);


  private final RootTaskRepository rootTaskRepository =
    SpringContextUtil.getBean(RootTaskRepository.class);

  private final ActorRef taskA = this.getContext().actorOf(Props.create(TaskA.class, taskId));
  private final ActorRef taskB = this.getContext().actorOf(Props.create(TaskB.class, taskId));

  /**
   * 子任务个数
   */
  private final Integer totalFinishCount = 2;
  /**
   * 记录已经完成的子任务数量
   */
  private Integer finishCount = 0;

  RootTask(String taskId) {
    super(taskId);
  }


  @Override public Receive createReceive() {
    return receiveBuilder().match(Calculate.class, calculate -> {
      doTask(calculate);
      taskA.tell(calculate, getSelf());
      taskB.tell(calculate, getSelf());
    }).matchEquals("finish", result -> {
      finishCount++;
      if (totalFinishCount.equals(finishCount)) {
        getContext().stop(self());
      }
    }).build();
  }

  @Override protected void doTask(Calculate calculate) {

    try {

      this.calculate = calculate;

      List<Analytics> analyticsList = analyticsRepository
        .findAnalyticsByPlaneUniqueNoEqualsAndTsBetween(calculate.getPlaneUniqueCode(),
          calculate.getStart(), calculate.getEnd());

      dataSize = (long) analyticsList.size();

      RootResult rootResult = new RootResult();
      rootResult.setId(taskId);
      analyticsList.stream().filter(item -> "click".equals(item.getType()))
        .forEach(item -> rootResult.addCount(1));

      resultSize = (long) rootResult.getCount();

      rootTaskRepository.save(rootResult);

      success = true;
      rootTask = true;

    } catch (Exception e) {
      logger.error("do task has error ,calculate:{}", calculate, e);
      TaskFactory.getTaskFinishListener().tell(
        new TaskExceptionData(taskId, calculate.getPlaneUniqueCode(), getTaskName(), calculate,
          e.getMessage()), getSelf());
    }
  }

  @Override public String getTaskName() {
    return "rootTask";
  }

}
