package com.vichen.akka.actor.analytics;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vichen
 */
@Repository
public interface AnalyticsRepository extends MongoRepository<Analytics, String> {

  /**
   * 根据飞机尾号，类型，ts时间段查询log
   *
   * @param planeUniqueNo
   * @param type
   * @param start
   * @param end
   * @return
   */
  List<Analytics> findAnalyticsByPlaneUniqueNoEqualsAndTypeEqualsAndTsBetween(String planeUniqueNo,
    String type, long start, long end);

  /**
   * 根据飞机尾号,ts时间段查询log
   *
   * @param planeUniqueNo
   * @param start
   * @param end
   * @return
   */
  List<Analytics> findAnalyticsByPlaneUniqueNoEqualsAndTsBetween(String planeUniqueNo, long start,
    long end);
}
