package com.vichen.akka.actor.task.finish;

import akka.actor.AbstractActor;
import com.alibaba.fastjson.JSON;
import com.vichen.SpringContextUtil;
import com.vichen.akka.actor.task.ReCalculate;
import com.vichen.akka.actor.task.index.TaskIndex;
import com.vichen.akka.actor.task.index.TaskIndexRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 输出全部计算结果
 */
public class TaskFinishListener extends AbstractActor {

  private final TaskFinishRepository taskFinishRepository =
    SpringContextUtil.getBean(TaskFinishRepository.class);
  private final TaskIndexRepository taskIndexRepository =
    SpringContextUtil.getBean(TaskIndexRepository.class);

  private final TaskExceptionRepository taskExceptionRepository =
    SpringContextUtil.getBean(TaskExceptionRepository.class);

  private static Logger logger = LoggerFactory.getLogger(TaskFinishListener.class);

  @Override public Receive createReceive() {
    return receiveBuilder().match(TaskFinishData.class, taskFinishData -> {
      logger.info("TaskFinishListener result:{}", JSON.toJSONString(taskFinishData));
      taskFinishRepository.save(taskFinishData);

      //根任务+不是重算
      if (taskFinishData.isRootTask() && !(taskFinishData.getCalculate() instanceof ReCalculate)) {
        taskIndexRepository.save(
          new TaskIndex(taskFinishData.getTaskName(), taskFinishData.getPlaneUniqueNo(),
            taskFinishData.getTaskId(), taskFinishData.getCalculate().getEnd()));
      }
    }).match(TaskExceptionData.class, taskExceptionRepository::save).build();
  }
}
