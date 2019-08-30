package com.vichen.akka.actor.task.flow;

import akka.actor.AbstractActor;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartBeat extends AbstractActor {

  Logger logger = LoggerFactory.getLogger(HeartBeat.class);

  @Override public Receive createReceive() {
    return receiveBuilder().matchAny(message -> {
      logger.info(JSON.toJSONString(message));
    }).build();
  }
}
