package org.dream.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.dream.common.entity.User;
import org.dream.dao.user.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tk.mybatis.mapper.entity.Example;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/content-service.xml" })
public class UserServiceTest {

	@Resource
	private IUserService userService;

	@Resource
	private UserMapper userMapper;

	@Test
	public void findUserById() {
		User user = userService.findUserId(52L);
		System.out.println(user.getWechatId() + " : " + user.getNickName());
		System.out.println(user);
	}

	@Test
	public void findUserByExample() {

		Example example = new Example(User.class);

		/* 排序 */
		example.setOrderByClause("id desc , real_name asc");

		example.createCriteria().andEqualTo("realName", "田广楠");

		List<User> list = userMapper.selectByExample(example);
		for (User u : list) {
			System.out.println(u.getRealName() + " : " + u.getId());
		}

	}

	@Test
	public void getUserChart() {
		List<User> list = userService.getUserChart("%Y");
		for (User u : list) {
			System.out.println(u.getCreateTime() + " : " + u.getParamMap().get("uTime"));
		}
	}

	@Test
	public void getUserWithAccount() {
		User user = userService.getUserWithAccount(52L);
		System.out.println(user);
	}
	
	

	@Test
	public void getUserById() {
		User user = userService.getUserById(52L);
		System.out.println(user);
	}
}
