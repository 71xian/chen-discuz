package cn.chenyuxian.discuz.system.core.log;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

/**
 * 日志管理器
 * @author chenyuxian
 * @date 2021-08-05
 */
public class LogManager {

	/**
	 * 异步操作记录日志的线程池
	 */
	private static final ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(10, new ScheduledExecutorFactoryBean());
	
	private LogManager() {}
	
	private static final LogManager LOG_MANAGER = new LogManager();
	
	public static LogManager me() {
		return LOG_MANAGER;
	}
	
	/**
	 * 异步执行日志的方法
	 * @param task
	 */
	private void executeLog(TimerTask task) {
		// 日志记录操作延时
		int operateDelayTime = 10;
		EXECUTOR.schedule(task, operateDelayTime, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 登录日志
	 * @param account
	 * @param success
	 * @param failMessage
	 */
	public void executeLoginLog(final String account, final String success, final String failMessage) {
	}
	
}
