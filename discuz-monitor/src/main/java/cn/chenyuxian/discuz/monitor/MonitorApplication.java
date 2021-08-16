package cn.chenyuxian.discuz.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAdminServer
public class MonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorApplication.class, args);
	}
}
