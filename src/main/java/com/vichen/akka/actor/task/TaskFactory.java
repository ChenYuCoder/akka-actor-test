package com.vichen.akka.actor.task;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vichen.akka.actor.task.finish.TaskFinishData;
import com.vichen.akka.actor.task.finish.TaskFinishListener;
import com.vichen.akka.actor.task.finish.TaskFinishRepository;
import com.vichen.akka.actor.task.root.RootTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service public class TaskFactory {

  @Autowired TaskFinishRepository taskFinishRepository;

  private static Logger logger = LoggerFactory.getLogger(TaskFactory.class);

  private static ActorSystem system;
  private static ActorRef taskFinishListener;

  /**
   * 支持外部触发的taskMap
   */
  private static final Map<String, Class> TASK_MAP = new HashMap<String, Class>() {{
    put("rootTask", RootTask.class);
  }};

  public TaskFactory() {
    // 创建Akka系统
    system = ActorSystem.create("flight-analytics-calc");
    logger.info("create flight-analytics-calc system");

    // 启动actor，驱动计算开始
    taskFinishListener =
      system.actorOf(Props.create(TaskFinishListener.class), "taskFinishListener");
  }

  /**
   * 重新计算某任务
   *
   * @param reCalculate 重新计算条件
   * @return 任务ID
   */

  public String reCalc(ReCalculate reCalculate) {
    String taskId = calc(reCalculate, reCalculate.getTaskName(), true);
    long timestamp = System.currentTimeMillis();
    TaskFinishData taskFinishData =
      new TaskFinishData(taskId, reCalculate.getPlaneUniqueCode(), "calcTask", 1L, 1L, timestamp,
        timestamp, true, reCalculate, false);

    taskFinishRepository.save(taskFinishData);

    return taskId;
  }

  /**
   * 全部计算
   *
   * @param calculate 计算条件
   * @return 任务ID
   */

  public String calc(Calculate calculate) {
    return calc(calculate, "rootTask", false);
  }

  /**
   * 指定计算某个类型任务
   *
   * @param calculate 计算条件
   * @param taskName  计算任务名称
   * @return 任务ID
   */

  public String calc(Calculate calculate, String taskName, boolean isReCalc) {

    //声明taskId
    String taskId =
      calculate.getPlaneUniqueCode() + "_" + calculate.getFlightId() + "_" + calculate.getStart()
        + "_" + calculate.getEnd();

    Class taskClazz = TASK_MAP.get(taskName);
    TaskFinishData taskFinishData =
      taskFinishRepository.findById(taskId + "_" + taskClazz.getName()).orElse(null);
    if (!isReCalc && taskFinishData != null && taskFinishData.isSuccess()) {
      logger.warn("task id:{} has done", taskId);
      return taskFinishData.getTaskId();
    }

    //声明任务
    ActorRef task = system.actorOf(Props.create(TASK_MAP.get(taskName), taskId), taskName + taskId);
    // 发送计算消息
    task.tell(calculate, ActorRef.noSender());
    return taskId;
  }

  public static ActorSystem getSystem() {
    return system;
  }

  public static ActorRef getTaskFinishListener() {
    return taskFinishListener;
  }

}
