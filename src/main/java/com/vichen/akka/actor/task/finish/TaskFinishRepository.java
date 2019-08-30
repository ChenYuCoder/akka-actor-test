package com.vichen.akka.actor.task.finish;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author vichen
 */
public interface TaskFinishRepository extends MongoRepository<TaskFinishData, String> {
}
