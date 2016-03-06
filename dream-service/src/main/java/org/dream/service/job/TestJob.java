package org.dream.service.job;

import java.util.Date;

import javax.annotation.Resource;

import org.dream.service.user.IUserService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class TestJob implements Job {

	public TestJob() {
		System.out.println("*****TestJob******");
	}

	@Resource
	private IUserService userService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		

		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		Long id = dataMap.getLongValue("id");
		String instName = context.getJobDetail().getName();
		System.out.println("系统当前时间 ： " + new Date());
		System.out.println(instName + "-->定时任务执行完毕！用户id : " + id);

		
	}

}
