package com.aoexe.discuz.system.core.aspectj;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.aoexe.discuz.core.annotion.Lock;
import com.aoexe.discuz.core.constant.AspectSort;

import lombok.extern.slf4j.Slf4j;

/**
 * 分布式锁处理
 *
 * @author chenyuxian
 * @date 2021-09-10 23:33:21
 */
@Aspect
@Order(AspectSort.LOCK)
@Component
@Slf4j
public class LockAspect {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Before("@annotation(lock)")
	public void before(JoinPoint joinPoint, Lock lock) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		String methodName = method.getName();
		String className = method.getDeclaringClass().getSimpleName();
		// 更新锁
		if ("write".equals(lock.type())) {
			while (!redisTemplate.boundValueOps("Lock:" + className).setIfAbsent("ok", 5, TimeUnit.SECONDS)) {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					log.info("lock:set {}", methodName);
				}
			}
		}

		// 读锁
		if ("read".equals(lock.type())) {
			while (Objects.nonNull(redisTemplate.boundValueOps("Lock:" + className).get())) {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					log.info("lock:get {}", methodName);
				}
			}
		}

	}
}
