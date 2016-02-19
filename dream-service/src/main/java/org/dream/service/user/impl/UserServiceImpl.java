package org.dream.service.user.impl;

import javax.annotation.Resource;

import org.dream.common.entity.User;
import org.dream.dao.user.UserMapper;
import org.dream.service.user.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public User findUserId(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

}
