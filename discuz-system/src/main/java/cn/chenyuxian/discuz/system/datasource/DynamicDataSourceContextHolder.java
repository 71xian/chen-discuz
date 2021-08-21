package cn.chenyuxian.discuz.system.datasource;

import lombok.extern.slf4j.Slf4j;

/**
 * 动态数据源切换
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Slf4j
public class DynamicDataSourceContextHolder {

	private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();
	
	public static void setDataSourceType(String dataSourceType) {
		log.info("切换到{}数据源", dataSourceType);
		CONTEXT_HOLDER.set(dataSourceType);
	}
	
	public static String getDataSourceType() {
		return CONTEXT_HOLDER.get();
	}
	
	public static void clearDataSourceType() {
		CONTEXT_HOLDER.remove();
	}
	
}
