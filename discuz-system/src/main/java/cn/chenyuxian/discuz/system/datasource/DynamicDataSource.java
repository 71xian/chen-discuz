package cn.chenyuxian.discuz.system.datasource;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

	public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSource) {
		super.setDefaultTargetDataSource(defaultTargetDataSource);
		super.setTargetDataSources(targetDataSource);
		super.afterPropertiesSet();
	}
	
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceContextHolder.getDataSourceType();
	}

}
