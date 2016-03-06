package org.dream.service.testentity;

import javax.annotation.Resource;

import org.dream.service.mongo.TestEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/content-service.xml" })
public class TestEntityTest {

	@Resource
	private TestEntityService entityService;

	@Test
	public void test() {
		System.out.println("******");
		entityService.saveTest();
	}

	@Test
	public void getTotalCount() {
		System.out.println("tatalCount: " + entityService.getTotalCount());
	}
}
