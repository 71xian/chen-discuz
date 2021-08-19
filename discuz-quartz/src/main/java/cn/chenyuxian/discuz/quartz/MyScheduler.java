package cn.chenyuxian.discuz.quartz;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MyScheduler {

	public static void main(String[] args) throws SchedulerException {
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		scheduler.start();
		JobDetail detail = JobBuilder.newJob(PrintWordsJob.class)
				.withIdentity("job1","group1")
				.build();
		
		Instant now = Instant.now();
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1")
				.startAt(Date.from(now.plusSeconds(3L)))
				.endAt(Date.from(now.plusSeconds(6L)))
				.withSchedule(SimpleScheduleBuilder
						.simpleSchedule()
						.withIntervalInSeconds(1)
						.repeatForever())
				.build();
		scheduler.scheduleJob(detail, trigger);
		System.out.println(LocalDateTime.now());
	}
}
