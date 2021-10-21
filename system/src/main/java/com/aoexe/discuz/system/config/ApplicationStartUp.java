package com.aoexe.discuz.system.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import com.aoexe.discuz.core.annotion.Permission;
import com.aoexe.discuz.core.util.RedisUtil;
import com.aoexe.discuz.system.modular.group.service.IGroupsService;
import com.aoexe.discuz.system.modular.setting.service.ISettingsService;

/**
 * 应用初始化配置
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
@Component
public class ApplicationStartUp implements ApplicationRunner {

	@Autowired
	private RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping;

	@Autowired
	private IGroupsService groupService;

	@Autowired
	private ISettingsService settingsService;

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<String> resources = getPermissions();
		groupService.editPermissions(1L, resources);
		settingsService.list().forEach(s -> {
			if (!s.getValue().isEmpty()) {
				redisUtil.set(s.getKey(), s.getValue(), 1L, TimeUnit.HOURS);
			}
		});
	}

	private List<String> getPermissions() {
		List<String> resources = new ArrayList<>();
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingInfoHandlerMapping.getHandlerMethods();
		handlerMethods.forEach((info, handlerMethod) -> {
			Permission permission = handlerMethod.getMethod().getAnnotation(Permission.class);
			if (Objects.isNull(permission)) {
				return;
			}
			resources.add(permission.value());
		});
		return resources;
	}

}
