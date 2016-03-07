package org.dream.service.job;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzUtil {
	
	private static SchedulerFactory ssf = new StdSchedulerFactory();

	public static Scheduler getInstance() {
		Scheduler sched = null;

		try {
			sched = ssf.getScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		return sched;
	}
}
