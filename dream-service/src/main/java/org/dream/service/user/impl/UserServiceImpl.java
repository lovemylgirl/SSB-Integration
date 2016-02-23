package org.dream.service.user.impl;

import javax.annotation.Resource;

import org.dream.common.entity.User;
import org.dream.dao.user.UserMapper;
import org.dream.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User findUserId(Long id) {
		logger.info("params : { userId = " + id + " }");
		return userMapper.selectByPrimaryKey(id);
	}

}
