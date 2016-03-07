package org.dream.service.mongo;

import java.util.List;

import org.bson.types.ObjectId;
import org.dream.mongodb.entity.MyEntity;

public interface IMyEntityService {

	long getTotalCount();

	List<MyEntity> findByName(String name);

	ObjectId save(String name, Integer age);

	List<MyEntity> findByNameAndAge(String name, Integer age);

	void deleteByName(String name);

}
