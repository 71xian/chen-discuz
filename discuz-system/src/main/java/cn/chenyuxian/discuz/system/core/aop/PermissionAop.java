package cn.chenyuxian.discuz.system.core.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import cn.chenyuxian.discuz.core.annotation.Permission;
import cn.chenyuxian.discuz.core.context.login.LoginContext;
import cn.chenyuxian.discuz.core.context.login.LoginContextHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * 权限过滤aop切面
 * @author chenyuxian
 * @date 2021-08-07
 */
@Aspect
@Slf4j
public class PermissionAop {

	private LoginContext me = LoginContextHolder.me();
	
	@Pointcut("@annotion(cn.chenyuxian.discuz.core.annotion.Permission)")
	private void getPermissionPointCut() {
		
	}
	
	/**
	 * 执行权限过滤
	 * @param joinPoint
	 */
	@Before("getPermissionPointCut()")
	public void doPermission(JoinPoint joinPoint) {
		boolean isSuperAdmin = me.isSuperAdmin();
		if(isSuperAdmin) {
			return;
		}
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		Permission permission = method.getAnnotation(Permission.class);
		
		String[] requireRoles = permission.role();
		
		
	}
}
