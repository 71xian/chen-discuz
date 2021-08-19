package cn.chenyuxian.discuz.monitor.druid;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

@WebFilter(
		filterName = "druidWebStatFilter", urlPatterns = "/*",
		initParams = {
				@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"),
				@WebInitParam(name = "async-supproted", value = "true")
		})
public class DruidFilter extends WebStatFilter{

}
