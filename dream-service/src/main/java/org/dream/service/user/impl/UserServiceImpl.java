package org.dream.service.user.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.dream.common.entity.User;
import org.dream.dao.user.UserMapper;
import org.dream.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;

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

}
