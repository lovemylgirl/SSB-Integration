package org.dream.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.dream.common.entity.User;
import org.dream.dao.user.UserMapper;
import org.dream.service.job.MyJobFactory;
import org.dream.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

@Service
public class UserServiceImpl implements IUserService {

	public UserServiceImpl() {
		System.out.println(String.format("%s excuted !", this.getClass().getSimpleName()));
	}

	@Resource
	private UserMapper userMapper;

	@Resource
	private MyJobFactory factory;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User findUserId(Long id) {
		logger.info("params : { userId = " + id + " }");
		LoggerContext ls = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(ls);
		return userMapper.selectByPrimaryKey(id);
	}

	@PostConstruct
	public void init() {
		System.out.println("UserServiceImpl init excuted ! ");
	}

	@PreDestroy
	public void destory() {
		System.out.println("UserServiceImpl destory excuted !");
	}

	@Override
	public List<User> findAll() {
		return userMapper.select(new User());
	}

	@Override
	public List<User> getUserChart(String type) {
		User user = new User();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("type", type);
		user.setParamMap(paramMap);
		List<User> list = userMapper.selectUserChart(user);
		return list;
	}

	@Override
	public User getUserWithAccount(Long userId) {
		User user = new User();
		user.setId(userId);
		return userMapper.getUserWithAccount(user);
	}

	@Override
	public User getUserById(Long userId) {
		User user = new User();
		user.setId(userId);
		return userMapper.getUserById(user);
	}

	@Override
	public int savaUser(User user) {
		return userMapper.saveUser(user);
	}

}
