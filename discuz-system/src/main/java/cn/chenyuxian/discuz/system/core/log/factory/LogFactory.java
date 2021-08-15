package cn.chenyuxian.discuz.system.core.log.factory;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;

import cn.chenyuxian.discuz.core.annotation.BusinessLog;
import cn.chenyuxian.discuz.core.util.JoinPointUtil;
import cn.chenyuxian.discuz.system.modular.log.entity.OperationLogs;

/**
 * 日志对象创建工厂
 * @author chenyuxian
 * @date 2021-08-05
 */
public class LogFactory {

	static void createSysLoginLog() {
		
	}
	
	/**
	 * 生成通用操作日志字段
	 * @param operationLogs
	 * @param businessLog
	 * @param joinPoint
	 */
	private static void fillOperationLog(OperationLogs operationLogs, BusinessLog businessLog, JoinPoint joinPoint) {
		//String className = joinPoint.getTarget().getClass().getName();
		
		String method = joinPoint.getSignature().getName();
		
		String input = JoinPointUtil.getArgsJsonString(joinPoint);
		
		operationLogs.setInput(input)
		.setMethod(method);
	}
	

	/**
	 * 基础操作日志
	 * @param path
	 * @param method
	 * @param ip
	 * @param input
	 * @param type
	 * @return
	 */
	public static OperationLogs operationLogs(String path, String method, String ip, String input, Integer type) {
		OperationLogs operationLogs = new OperationLogs();
		operationLogs.setPath(path)
		.setMethod(method)
		.setIp(ip)
		.setInput(input);
		return operationLogs;
	}
	
}
