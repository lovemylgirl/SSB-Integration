package org.dream.dao.user;

import java.util.List;

import org.dream.common.entity.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

	List<User> selectUserChart(User user);
	
	User getUserWithAccount(User user);
	
	User getUserById(User user);
}
