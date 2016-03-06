package org.dream.service.user.impl;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.dream.common.entity.User;
import org.dream.common.util.DateUtil;
import org.dream.common.util.QuartzManager;
import org.dream.dao.user.UserMapper;
import org.dream.service.job.MyJobFactory;
import org.dream.service.job.QuartzUtil;
import org.dream.service.job.TestJob;
import org.dream.service.user.IUserService;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

@Service
public class UserServiceImpl implements IUserService {

	public UserServiceImpl() {
		System.out.println("*****UserServiceImpl*****");
	}

	@Resource
	private UserMapper userMapper;

	@Resource
	private MyJobFactory factory;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User findUserId(Long id) {

		logger.info("params : { userId = " + id + " }");
		LoggerContext ls = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(ls);

		String jobName = id + "%%" + System.currentTimeMillis();

		JobDetail jobDetail = new JobDetail(jobName, QuartzManager.JOB_GROUP_NAME, TestJob.class);
		jobDetail.getJobDataMap().put("id", id);

		Scheduler scheduler = QuartzUtil.getInstance();

		try {
			scheduler.setJobFactory(factory);

			SimpleTrigger trigger = new SimpleTrigger(jobName, "trigger1");
			trigger.setStartTime(DateUtil.addSeconds(new Date(), 20));

			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.start();

		} catch (SchedulerException e1) {

			e1.printStackTrace();
		}

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
