package com.aoexe.discuz.system.core.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import com.aoexe.discuz.core.constant.AspectSort;

@Aspect
@Order(AspectSort.DATA_SCOPE)
public class DataScopeAspect {

	@Pointcut("@annotation(com.aoexe.discuz.core.annotion.DataScope)")
	private void pointcut() {}
	
	@Before("pointcut()")
	public void before(JoinPoint point) {
		
	}
}
