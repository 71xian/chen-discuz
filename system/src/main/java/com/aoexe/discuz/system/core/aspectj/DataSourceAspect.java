package com.aoexe.discuz.system.core.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.aoexe.discuz.core.annotion.DataSource;
import com.aoexe.discuz.core.constant.AspectSort;
import com.aoexe.discuz.system.core.datasource.DynamicDataSourceContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据源切换
 * 
 * @author chenyuxian
 * @date 2021-09-05 10:15:01
 */
@Aspect
@Order(AspectSort.DATA_SOURCE)
@Component
@Slf4j
public class DataSourceAspect {

	@Before("@annotation(dataSource)")
	public void switchDataSource(JoinPoint joinPoint, DataSource dataSource) {
		if (!DynamicDataSourceContextHolder.containDataSourceKey(dataSource.value().name())) {
			log.error("DataSource [{}] 不存在，使用默认 DataSource [{}] ", dataSource.value(),
					DynamicDataSourceContextHolder.getDataSourceKey());
		} else {
			DynamicDataSourceContextHolder.containDataSourceKey(dataSource.value().name());
			log.debug("切换 DataSource 至 [{}] ，引起切换方法是 [{}]", DynamicDataSourceContextHolder.getDataSourceKey(),
					joinPoint.getSignature());
		}
	}

	@After("@annotation(dataSource)")
	public void restoreDataSource(JoinPoint joinPoint, DataSource dataSource) {
		// 将数据源置为默认数据源
		DynamicDataSourceContextHolder.clearDataSourceKey();
		log.debug("重置 DataSource 至 [{}] ，引起重置的方法是 [{}]", DynamicDataSourceContextHolder.getDataSourceKey(),
				joinPoint.getSignature());
	}
}
