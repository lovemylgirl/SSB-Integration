package org.dream.service.mongo.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.dream.mongodb.dao.MyEntityDao;
import org.dream.mongodb.entity.MyEntity;
import org.dream.service.mongo.IMyEntityService;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import org.springframework.stereotype.Service;

@Service
public class MyEntityServiceImpl implements IMyEntityService {

	@Resource
	private MyEntityDao entityDao;

	@Override
	public long getTotalCount() {
		return entityDao.count();
	}

	@Override
	public List<MyEntity> findByName(String name) {
		Query<MyEntity> query = entityDao.createQuery().field("name").equal(name).order("-createTime");
		QueryResults<MyEntity> result = entityDao.find(query);
		return result.asList();
	}

	@Override
	public ObjectId save(String name, Integer age) {
		MyEntity entity = new MyEntity();
		entity.setAge(age);
		entity.setName(name);
		entity.setCreateTime(new Date());
		return (ObjectId) entityDao.save(entity).getId();
	}

	@Override
	public List<MyEntity> findByNameAndAge(String name, Integer age) {
		Query<MyEntity> query = entityDao.createQuery().filter("name", name).filter("age <", age).order("createTime");
		QueryResults<MyEntity> result = entityDao.find(query);
		return result.asList();
	}

	@Override
	public void deleteByName(String name) {
		Query<MyEntity> query = entityDao.createQuery().filter("name", name);
		entityDao.deleteByQuery(query);
	}

}
