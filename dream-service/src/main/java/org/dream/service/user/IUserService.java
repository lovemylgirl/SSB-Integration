package org.dream.service.user;

import java.util.List;

import org.dream.common.entity.User;

public interface IUserService {

	User findUserId(Long id);

	List<User> findAll();

	List<User> getUserChart(String type);

	User getUserWithAccount(Long userId);
	
	User getUserById(Long userId);
	
	int savaUser(User user);
}
