package com.aoexe.discuz.system.core.aspectj;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.aoexe.discuz.core.constant.AspectSort;
import com.aoexe.discuz.core.util.IpUtil;
import com.aoexe.discuz.core.util.RequestUtil;
import com.aoexe.discuz.core.util.SpringUtil;
import com.aoexe.discuz.system.modular.log.entity.OperationLog;
import com.aoexe.discuz.system.modular.log.service.IOperationLogService;
import com.aoexe.discuz.system.modular.log.service.impl.OperationLogServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 业务日志切面
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Aspect
@Slf4j
@Component
@Order(AspectSort.BUSINESS_LOG)
public class BusinessLogAspect {

	@Autowired
	private IOperationLogService operationLogService;

	@Pointcut("@annotation(com.aoexe.discuz.core.annotion.BusinessLog)")
	private void pointcut() {
	}

	@AfterReturning(pointcut = "pointcut()", returning = "result")
	public void afterReturning(JoinPoint point, Object result) throws Throwable {
		HttpServletRequest request = RequestUtil.getRequest();
		SpringUtil.getBean(OperationLogServiceImpl.class);
		String method = request.getMethod();
		String ip = IpUtil.getIp(request);
		String path = request.getRequestURI();
		OperationLog operationLog = new OperationLog();
		operationLog.setCreatedAt(LocalDateTime.now());
		operationLog.setUserId(0L);
		operationLog.setInput(request.getProtocol());
		operationLog.setMethod(method);
		operationLog.setPath(path);
		operationLog.setIp(ip);
		operationLog.setType(0);
		log.debug("BusinessLog: ip:{}, method:{}, path:{}", ip, method, path);
		operationLogService.save(operationLog);
	}

}
