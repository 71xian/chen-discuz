package cn.chenyuxian.discuz.system.core.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import cn.chenyuxian.discuz.core.annotation.BusinessLog;

/**
 * 业务日志aop切面
 * 
 * @author chenyuxian
 * @date 2021-08-05
 */
@Aspect
@Order
public class BusinessLogAop {

	/**
	 * 日志切入点
	 */
	@Pointcut("@annotion(cn.chenyuxian.discuz.core.annotion.BusinessLog)")
	private void getLogPointCut() {

	}

	/**
	 * 操作成功返回结果记录日志
	 * 
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(pointcut = "getLogPointCut()", returning = "result")
	public void doAfterReturning(JoinPoint joinPoint, Object result) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		BusinessLog businessLog = method.getAnnotation(BusinessLog.class);
		System.out.println(businessLog.title());
	}

	/**
	 * 操作发生异常记录日志
	 * 
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(pointcut = "getLogPointCut()", throwing = "exception")
	public void doAfterThrowing(JoinPoint joinPoint, Exception exception) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		BusinessLog businessLog = method.getAnnotation(BusinessLog.class);
		System.out.println(businessLog.title());
	}

}
