package org.dream.service.user;

import javax.annotation.Resource;

import org.dream.common.entity.UserAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/content-service.xml" })
public class UserAccountTest {

	@Resource
	private IUserAccountService accountService;

	@Test
	public void getAccountById() {
		UserAccount account = accountService.getAccountById(1L);
		System.out.println(account);
	}
}
