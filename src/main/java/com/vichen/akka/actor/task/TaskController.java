package com.vichen.akka.actor.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vichen
 */
@RestController @RequestMapping(value = "/task") public class TaskController {

  @Autowired TaskFactory taskFactory;

  @RequestMapping("/reCalc")
  public String reCalc(ReCalculate reCalculate) {
    return taskFactory.reCalc(reCalculate);
  }

}
