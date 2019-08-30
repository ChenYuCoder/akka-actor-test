package com.vichen.akka.actor.task.root.taskA;

import com.vichen.akka.actor.task.AbstractTask;
import com.vichen.akka.actor.task.Calculate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskA extends AbstractTask {

  private static Logger logger = LoggerFactory.getLogger(TaskA.class);

  TaskA(String taskId) {
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
    return "taskA";
  }

}
