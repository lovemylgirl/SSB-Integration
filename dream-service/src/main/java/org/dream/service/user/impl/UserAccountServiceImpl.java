package org.dream.service.user.impl;

import javax.annotation.Resource;

import org.dream.common.entity.UserAccount;
import org.dream.dao.user.UserAccountMapper;
import org.dream.service.user.IUserAccountService;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

	@Resource
	private UserAccountMapper accountMapper;

	@Override
	public UserAccount getAccountById(Long id) {
		UserAccount account = new UserAccount();
		account.setId(id);
		return accountMapper.getAccountById(account);
	}

}
