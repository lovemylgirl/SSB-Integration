package org.dream.service.user.task;

import java.util.TimerTask;

import javax.annotation.Resource;

import org.dream.common.entity.User;
import org.dream.service.user.IUserService;

public class UserTask extends TimerTask {

	@Resource
	private IUserService userService;

	private Long userId;

	@Override
	public void run() {
		User user = userService.findUserId(userId);
		System.out.println(user.getRealName());
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
