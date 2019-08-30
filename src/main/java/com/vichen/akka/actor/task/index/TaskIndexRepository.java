package com.vichen.akka.actor.task.index;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author vichen
 */
public interface TaskIndexRepository extends MongoRepository<TaskIndex, String> {

}
