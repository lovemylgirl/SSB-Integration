package org.dream.mongodb.dao;

import org.bson.types.ObjectId;
import org.dream.mongodb.entity.MyEntity;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.stereotype.Repository;

@Repository
public class MyEntityDao extends BasicDAO<MyEntity, ObjectId> {
	
	protected MyEntityDao(Datastore ds) {
		super(ds);
	}

}
