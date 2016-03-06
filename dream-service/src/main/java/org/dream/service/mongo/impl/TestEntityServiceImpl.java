package org.dream.service.mongo.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.dream.mongodb.dao.TestEntityDao;
import org.dream.mongodb.entity.TestEntity;
import org.dream.service.mongo.TestEntityService;
import org.springframework.stereotype.Service;

@Service
public class TestEntityServiceImpl implements TestEntityService {

	@Resource
	private TestEntityDao entityDao;

	public void saveTest() {
		TestEntity entity = new TestEntity();
		entity.setCreateTime(new Date());
		entity.setName("ahua");
		entityDao.save(entity);
	}

	@Override
	public long getTotalCount() {
		return entityDao.count();
	}

}
