package org.dream.service.testentity;

import java.util.List;

import javax.annotation.Resource;

import org.dream.mongodb.entity.MyEntity;
import org.dream.service.mongo.IMyEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/content-service.xml" })
public class MyEntityTest {

	@Resource
	private IMyEntityService entityService;

	@Test
	public void getTotalCount() {
		System.out.println("tatalCount: " + entityService.getTotalCount());
	}

	@Test
	public void findByName() {
		List<MyEntity> entities = entityService.findByName("ahua");
		for (MyEntity t : entities) {
			System.out.println(t.getCreateTime());
		}
	}

	@Test
	public void save() {
		System.out.println(entityService.save("dali", 20));
	}

	@Test
	public void findByNameAndAge() {
		List<MyEntity> entities = entityService.findByNameAndAge("ahua", 20);
		for (MyEntity t : entities) {
			System.out.println(t.getCreateTime() + " : " + t.getName());
		}
	}

	@Test
	public void deleteByName() {
		entityService.deleteByName("dali");
	}
}
