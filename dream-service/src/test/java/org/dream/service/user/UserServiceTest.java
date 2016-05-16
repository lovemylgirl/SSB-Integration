package org.dream.service.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dream.common.entity.User;
import org.dream.dao.user.UserMapper;
import org.junit.Ignore;
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

	
	/**
	 * ########使用了自定义的 TypeHandler 这个方法
	 * 
	 * 或者使用注解的方式，没试验
	 * 
	 * http://www.tuicool.com/articles/zYz6je
	 * 
	 * @MappedTypes @MappedJdbcTypes
	 * */
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

	/**
	 * 关联的结果查询
	 */
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

	@Test
	@Ignore/***/
	public void saveUser() {
		User user = new User();
		user.setCreateTime(new Date());
		user.setHeadImgUrl("");
		user.setMacId("");
		user.setMobile("15888836738");
		user.setNickName("阿花");
		user.setRealName("田广楠");
		user.setToken((byte) 4);
		user.setUpdateTime(new Date());
		user.setUserStatus(false);
		user.setWechatId("22345678");/*此属性有唯一索引，请注意*/
		user.setIsOwner(true);
		userService.savaUser(user);
	}
}
