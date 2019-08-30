package com.vichen.akka.actor.task.root;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author vichen
 */
public interface RootTaskRepository extends MongoRepository<RootResult, String> {

}
