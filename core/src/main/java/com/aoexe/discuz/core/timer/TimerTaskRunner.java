package com.aoexe.discuz.core.timer;

/**
 * 定时器任务执行者
 *
 * @author chenyuxian
 * @date 2021-08-27
 */
public interface TimerTaskRunner {
	
	/**
	 * 任务执行的具体内容
	 *
	 * @author chenyuxian
	 * @date 2021-08-27
	 */
	void action();
}
