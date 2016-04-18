package org.dream.service.shiro;

import javax.annotation.Resource;

import org.dream.common.entity.ShiroUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/content-service.xml" })
public class ShiroUserTest {

	@Resource
	private IShiroUserService iShiroUserService;

	@Test
	public void testCreateUser() {
		ShiroUser shiroUser = new ShiroUser();
		String username = "admin";
		Assert.state(iShiroUserService.findByUsername(username) == null, "用户已经存在！");
		shiroUser.setUsername(username);
		shiroUser.setPassword(username);
		iShiroUserService.createUser(shiroUser);
	}
}
