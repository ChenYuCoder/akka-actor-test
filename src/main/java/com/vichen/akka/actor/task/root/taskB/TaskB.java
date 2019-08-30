package com.vichen.akka.actor.task.root.taskB;

import com.vichen.akka.actor.task.AbstractTask;
import com.vichen.akka.actor.task.Calculate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskB extends AbstractTask {

  private static Logger logger = LoggerFactory.getLogger(TaskB.class);

  TaskB(String taskId) {
    super(taskId);
  }


  @Override public Receive createReceive() {
    return receiveBuilder().match(Calculate.class, calculate -> {
      doTask(calculate);
      sender().tell("finish", self());
      getContext().stop(getSelf());
    }).build();
  }

  @Override public String getTaskName() {
    return "taskB";
  }

}
