package com.aoexe.discuz.system.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.aoexe.discuz.core.annotion.DataSource.SourceName;
import com.aoexe.discuz.system.core.datasource.DynamicDataSource;

/**
 * druid配置多数据源
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Configuration
@Profile("prod")
public class DruidConfig {
	
	@Bean(destroyMethod = "close", initMethod = "init")
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSource master() {
		return druidDataSource();
	}
	
	@Bean(destroyMethod = "close", initMethod = "init")
	@ConfigurationProperties(prefix = "spring.datasource.slave")
	public DataSource slave() {
		return druidDataSource();
	}
	
	@Bean
	@Primary  //自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
	public DataSource dataSource() {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		Map<Object, Object> dataSourceMap = new HashMap<>(2);
		dataSourceMap.put(SourceName.MASTER.name(), master());
		dataSourceMap.put(SourceName.SLVAE.name(), slave());
		// 将master作为默认的数据源
		dynamicDataSource.setDefaultTargetDataSource(master());
		// 将master和slave作为指定的数据源
		dynamicDataSource.setTargetDataSources(dataSourceMap);
		return dynamicDataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	public DataSource druidDataSource() {
		return new DruidDataSource();
	}

}
