package com.aoexe.discuz.system.core.log;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

import com.aoexe.discuz.core.context.security.SecurityContextHolder;
import com.aoexe.discuz.core.util.IpUtil;
import com.aoexe.discuz.core.util.RequestUtil;
import com.aoexe.discuz.system.modular.user.entity.User;

/**
 * 日志管理器
 *
 * @author chenyuxian
 * @date 2021-08-27
 */
public class LogManager {

	private static final ScheduledThreadPoolExecutor EXCUTOR = new ScheduledThreadPoolExecutor(10,
			new ScheduledExecutorFactoryBean());
	
	private LogManager() {}
	
	private static final LogManager LOG_MANAGER = new LogManager();
	
	public static LogManager me() {
		return LOG_MANAGER;
	}
	
	public void failLoginLog(String username) {
		HttpServletRequest request = RequestUtil.getRequest();
		String ip = IpUtil.getIp(request);
		Long userId = ((User)SecurityContextHolder.getContext().getLoginUser()).getId();
		
		
	}
}
