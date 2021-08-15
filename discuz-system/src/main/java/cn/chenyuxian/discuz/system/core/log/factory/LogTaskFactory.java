package cn.chenyuxian.discuz.system.core.log.factory;

import java.util.TimerTask;

import org.aspectj.lang.JoinPoint;

import cn.chenyuxian.discuz.core.annotation.BusinessLog;
import cn.chenyuxian.discuz.core.util.SpringUtil;
import cn.chenyuxian.discuz.system.modular.log.entity.OperationLogs;
import cn.chenyuxian.discuz.system.modular.log.service.IOperationLogsService;
import cn.chenyuxian.discuz.system.modular.log.service.IUserActionLogsService;
import lombok.extern.slf4j.Slf4j;

/**
 * 日志操作任务创建工厂
 * @author chenyuxian
 * @date 2021-08-06
 */
@Slf4j
public class LogTaskFactory {

	private static final IOperationLogsService OPERATION_LOGS_SERVICE = SpringUtil.getBean(IOperationLogsService.class);
	
	private static final IUserActionLogsService USER_ACTION_LOGS_SERVICE = SpringUtil.getBean(IUserActionLogsService.class);
	
	public static TimerTask opeartion(OperationLogs operationLogs, BusinessLog businessLog, JoinPoint joinPoint) {
		return new TimerTask() {
			
			@Override
			public void run() {
				try {
					
				}catch (Exception e) {
					log.error(">>> 创建操作日志异常，请求号为:{}, 具体信息为:{}");
				}
			}
		};
	}
}
