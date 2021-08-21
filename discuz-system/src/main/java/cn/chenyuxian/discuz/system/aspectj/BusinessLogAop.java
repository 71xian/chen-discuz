package cn.chenyuxian.discuz.system.aspectj;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import cn.chenyuxian.discuz.core.utils.IpUtils;
import cn.chenyuxian.discuz.core.utils.RedisUtil;
import cn.chenyuxian.discuz.core.utils.RequestUtils;
import cn.chenyuxian.discuz.core.utils.SpringUtils;
import cn.chenyuxian.discuz.system.modular.log.entity.OperationLog;
import cn.chenyuxian.discuz.system.modular.log.service.IOperationLogService;
import cn.chenyuxian.discuz.system.modular.log.service.impl.OperationLogServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * 业务日志切面
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Aspect
@Component
@Slf4j
public class BusinessLogAop {

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private IOperationLogService operationLogService;

	@Pointcut("@annotation(cn.chenyuxian.discuz.core.annotion.BusinessLog)")
	private void pointcut() {
	}

	@AfterReturning(pointcut = "pointcut()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) throws Throwable {
		HttpServletRequest request = RequestUtils.getRequest();
		SpringUtils.getBean(OperationLogServiceImpl.class);
		String method = request.getMethod();
		String ip = IpUtils.getIp(request);
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
