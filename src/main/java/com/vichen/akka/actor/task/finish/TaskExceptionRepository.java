package com.vichen.akka.actor.task.finish;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author vichen
 */
public interface TaskExceptionRepository extends MongoRepository<TaskExceptionData, String> {
}
