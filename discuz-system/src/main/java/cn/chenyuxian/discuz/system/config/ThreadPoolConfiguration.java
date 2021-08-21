package cn.chenyuxian.discuz.system.config;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程池配置
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Configuration
@Slf4j
public class ThreadPoolConfiguration {

	/**
	 * 核心线程池大小
	 */
	private int corePoolSize = 50;

	/**
	 * 最大可创建的线程数
	 */
	private int maxPoolSize = 200;

	/**
	 * 队列最大长度
	 */
	private int queueCapacity = 1000;

	/**
	 * 线程池维护线程所允许的空闲时间
	 */
	private int keepAliveSeconds = 300;

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setMaxPoolSize(maxPoolSize);
		executor.setCorePoolSize(corePoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setKeepAliveSeconds(keepAliveSeconds);
		// 线程池对拒绝任务(无线程可用)的处理策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}

	@Bean
	public ScheduledExecutorService scheduledExecutorService() {
		return new ScheduledThreadPoolExecutor(corePoolSize,
				new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build()) {
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute(r, t);
				if (t == null && r instanceof Future<?>) {
					try {
						Future<?> future = (Future<?>) r;
						if (future.isDone()) {
							future.get();
						}
					} catch (CancellationException ce) {
						t = ce;
					} catch (ExecutionException ee) {
						t = ee.getCause();
					} catch (InterruptedException ie) {
						Thread.currentThread().interrupt();
					}
				}
				if (t != null) {
					log.error(t.getMessage(), t);
				}
			}
		};
	}
}
