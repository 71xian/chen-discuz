package cn.chenyuxian.discuz.monitor.druid;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

@WebServlet(
		urlPatterns = "/druid/*",
		initParams = {
				@WebInitParam(name = "allow", value = "127.0.0.1"),
				@WebInitParam(name = "loginUsername", value = "admin"),
				@WebInitParam(name = "loginPassword", value = "admin"),
				@WebInitParam(name = "resetEnable", value = "false"),
				@WebInitParam(name = "session-stat-max-count", value = "1000")
})
public class DruidServlet extends StatViewServlet{

	private static final long serialVersionUID = 1L;

	
}
