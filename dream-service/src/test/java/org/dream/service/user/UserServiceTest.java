package org.dream.service.user;

import javax.annotation.Resource;

import org.dream.common.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/content-service.xml" })
public class UserServiceTest {

	@Resource
	private IUserService userService;

	@Test
	public void findUserById() {
		User user = userService.findUserId(52L);
		System.out.println(user.getWechatId() + " : " + user.getNickName());
	}

}
