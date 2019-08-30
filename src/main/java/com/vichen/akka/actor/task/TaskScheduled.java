package com.vichen.akka.actor.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author vichen
 */
@Component @Configuration @EnableScheduling public class TaskScheduled {

  Logger logger = LoggerFactory.getLogger(TaskFactory.class);

  @Autowired TaskFactory taskFactory;


  @Scheduled(cron = "0 * * * * ?") private void configureTasks() {
    logger.info("执行静态定时任务时间: " + LocalDateTime.now());
    taskFactory.calc(new Calculate("B-0001", 1L, 1L, "FL000001"));
  }
}
