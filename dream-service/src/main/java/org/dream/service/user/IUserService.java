package org.dream.service.user;

import java.util.List;

import org.dream.common.entity.User;

public interface IUserService {
	
	User findUserId(Long id);
	
	List<User> findAll();
	
}
