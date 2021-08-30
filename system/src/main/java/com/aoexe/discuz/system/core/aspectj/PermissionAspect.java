package com.aoexe.discuz.system.core.aspectj;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.AspectSort;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.context.security.SecurityContextHolder;
import com.aoexe.discuz.core.util.RequestUtil;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;

@Aspect
@Component
@Order(AspectSort.PERMISSION)
public class PermissionAspect {
	
	@Pointcut("@annotation(com.aoexe.discuz.core.annotion.Permission)")
	private void pointcut() {
		
	}
	
	@Before(value = "pointcut()")
	public void before(JoinPoint point) {
		HttpServletRequest request = RequestUtil.getRequest();
		
		throw new BaseException(ResponseEnum.UNAUTHORIZED);
	}
	
}
