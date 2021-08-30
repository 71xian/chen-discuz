package com.aoexe.discuz.system.core.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.aoexe.discuz.core.constant.AspectSort;
import com.aoexe.discuz.core.context.security.SecurityContextHolder;

@Aspect
@Component
@Order(AspectSort.EXCEPTION)
public class ExceptionAspect {

	@Pointcut("@annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
	private void pointcut() {

	}

	@Before(value = "pointcut()")
	public void before(JoinPoint point) {
		System.out.println("清除context");
		SecurityContextHolder.clearContext();
	}
}
