package org.dream.common.util;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzManager {

	private static SchedulerFactory sf = new StdSchedulerFactory();
	public static final String JOB_GROUP_NAME = "group1";
	private static final String TRIGGER_GROUP_NAME = "trigger1";

	private static final Logger logger = LoggerFactory.getLogger(QuartzManager.class);

	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 
	 * @param jobName
	 * @param job
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void addJob(String jobName, Job job, String time) throws SchedulerException, ParseException {
		Scheduler scheduler = sf.getScheduler();

		// 任务名，任务组，任务执行类
		JobDetail detail = new JobDetail(jobName, JOB_GROUP_NAME, job.getClass());

		// 触发器名,触发器组
		CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);

		// 触发器时间设定
		trigger.setCronExpression(time);
		scheduler.scheduleJob(detail, trigger);

		// 启动
		if (!scheduler.isShutdown()) {
			scheduler.start();
		}
	}

	public static void addJob(String jobName, JobDetail jobDetail, Date time)
			throws SchedulerException, ParseException {
		
		Scheduler scheduler = sf.getScheduler();

		// 触发器名,触发器组
		SimpleTrigger trigger = new SimpleTrigger(jobName, TRIGGER_GROUP_NAME);

		// 触发器时间设定
		trigger.setStartTime(time);
		scheduler.scheduleJob(jobDetail, trigger);

		// 启动
		if (!scheduler.isShutdown()) {
			logger.info(jobName + "-->" + jobDetail.getJobDataMap().getString(jobName) + "-->定时任务启动！ " + new Date());
			scheduler.start();
		}
	}

	/**
	 * 添加一个定时任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 * @param job
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Job job,
			String time) throws SchedulerException, ParseException {
		Scheduler sched = sf.getScheduler();

		// 任务名，任务组，任务执行类
		JobDetail jobDetail = new JobDetail(jobName, jobGroupName, job.getClass());

		// 触发器
		CronTrigger trigger = new CronTrigger(triggerName, triggerGroupName);// 触发器名,触发器组

		// 触发器时间设定
		trigger.setCronExpression(time);
		sched.scheduleJob(jobDetail, trigger);

		if (!sched.isShutdown())
			sched.start();
	}

	/** */
	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @param time
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void modifyJobTime(String jobName, String time) throws SchedulerException, ParseException {
		Scheduler sched = sf.getScheduler();
		Trigger trigger = sched.getTrigger(jobName, TRIGGER_GROUP_NAME);
		if (trigger != null) {
			CronTrigger ct = (CronTrigger) trigger;
			ct.setCronExpression(time);
			sched.resumeTrigger(jobName, TRIGGER_GROUP_NAME);
		}
	}

	/**
	 * 修改一个任务的触发时间
	 * 
	 * @param triggerName
	 * @param triggerGroupName
	 * @param time
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void modifyJobTime(String triggerName, String triggerGroupName, String time)
			throws SchedulerException, ParseException {
		Scheduler sched = sf.getScheduler();
		Trigger trigger = sched.getTrigger(triggerName, triggerGroupName);
		if (trigger != null) {
			CronTrigger ct = (CronTrigger) trigger;
			// 修改时间
			ct.setCronExpression(time);
			// 重启触发器
			sched.resumeTrigger(triggerName, triggerGroupName);
		}
	}

	/** */
	/**
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @throws SchedulerException
	 */
	public static void removeJob(String jobName) throws SchedulerException {
		Scheduler sched = sf.getScheduler();
		sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);// 停止触发器
		sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);// 移除触发器
		sched.deleteJob(jobName, JOB_GROUP_NAME);// 删除任务
	}

	/** */
	/**
	 * 移除一个任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 * @throws SchedulerException
	 */
	public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName)
			throws SchedulerException {

		Scheduler sched = sf.getScheduler();

		// 停止触发器
		sched.pauseTrigger(triggerName, triggerGroupName);

		// 移除触发器
		sched.unscheduleJob(triggerName, triggerGroupName);

		// 删除任务
		sched.deleteJob(jobName, jobGroupName);
	}
}
