package com.aoexe.discuz.system.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.aoexe.discuz.system.config.properties.DruidProperties;

/**
 * druid配置多数据源
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Configuration
public class DruidConfiguration {

	 /**
     * druid配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidProperties druidProperties() {
        return new DruidProperties();
    }

    /**
     * druid数据库连接池
     */
    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties().config(dataSource);
        return dataSource;
    }

	/**
	 * druid监控，配置StatViewServlet
	 *
	 * @author xuyuxiang
	 * @date 2020/6/28 16:03
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> druidServletRegistration() {

		// 设置servelet的参数
		HashMap<String, String> statViewServletParams = new HashMap<>(4);
		statViewServletParams.put("resetEnable", "true");
		statViewServletParams.put("loginUsername", "admin");
		statViewServletParams.put("loginPassword", "admin");

		ServletRegistrationBean<StatViewServlet> registration = new ServletRegistrationBean<>(new StatViewServlet());
		registration.addUrlMappings("/druid/*");
		registration.setInitParameters(statViewServletParams);
		return registration;
	}

	@Bean
	public FilterRegistrationBean<WebStatFilter> webStatFilter() {
		FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new WebStatFilter());
		Map<String, String> initParams = new HashMap<>(1);
		initParams.put("exclusions", "*.vue,*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
		bean.setInitParameters(initParams);
		bean.setUrlPatterns(Arrays.asList("/*"));
		return bean;
	}
}
