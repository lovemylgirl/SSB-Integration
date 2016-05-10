package org.dream.dao.user;

import org.dream.common.entity.UserAccount;

import tk.mybatis.mapper.common.Mapper;

public interface UserAccountMapper extends Mapper<UserAccount> {
	
	UserAccount getAccountById(UserAccount account);
	
}
