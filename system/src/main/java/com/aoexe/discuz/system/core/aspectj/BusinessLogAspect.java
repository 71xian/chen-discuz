package com.aoexe.discuz.system.core.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.aoexe.discuz.core.constant.AspectSort;

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

	@Pointcut("@annotation(com.aoexe.discuz.core.annotion.BusinessLog)")
	private void pointcut() {
	}

	@AfterReturning(pointcut = "pointcut()", returning = "result")
	public void afterReturning(JoinPoint point, Object result) throws Throwable {

	}

}
