package org.dream.mongodb.dao;

import org.bson.types.ObjectId;
import org.dream.mongodb.entity.TestEntity;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class TestEntityDao extends BasicDAO<TestEntity, ObjectId> {

	protected TestEntityDao(Datastore ds) {
		super(ds);
	}

}
