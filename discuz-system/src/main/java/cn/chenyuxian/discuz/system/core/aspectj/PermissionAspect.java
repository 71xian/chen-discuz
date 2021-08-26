package cn.chenyuxian.discuz.system.core.aspectj;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.chenyuxian.discuz.core.annotion.Permission;
import cn.chenyuxian.discuz.core.base.exception.BaseException;
import cn.chenyuxian.discuz.core.constant.AspectSort;
import cn.chenyuxian.discuz.core.constant.ErrorCode;
import cn.chenyuxian.discuz.core.context.security.SecurityContextHolder;
import cn.chenyuxian.discuz.system.modular.user.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * 权限校验切面
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
@Aspect
@Order(AspectSort.PERMISSION)
@Component
@Slf4j
public class PermissionAspect {

	@Pointcut("@annotation(cn.chenyuxian.discuz.core.annotion.Permission)")
	private void pointcut() {
	}

	@Before(value = "pointcut()")
	public void before(JoinPoint point) {
		User user = (User) SecurityContextHolder.getContext().getLoginUser();
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		Permission permission = method.getDeclaringClass().getAnnotation(Permission.class);
		Permission MethodPermission = method.getAnnotation(Permission.class);
		// 方法注解优先级大于类注解
		if(MethodPermission != null) {
			permission = MethodPermission;
		}
		
		String requireRole = permission.role();
		if(!requireRole.isEmpty() && requireRole.equals(user.getRole())) {
			return;
		}
		
		// 权限字符串
		String permStr = method.getDeclaringClass().getSimpleName() + ":" + method.getName();
		if(!user.getPermissions().contains(permStr)) {
			log.error(">>> 没有权限");
			throw new BaseException(ErrorCode.UNAUTHORIZED);
		}
	}
}
