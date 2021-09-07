package com.aoexe.discuz.system.config;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import com.aoexe.discuz.core.annotion.Permission;
import com.aoexe.discuz.system.core.cache.ConfigCache;
import com.aoexe.discuz.system.modular.config.service.IConfigService;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;

/**
 * 应用初始化配置
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
@Component
public class ApplicationStartUp implements ApplicationRunner {

	@Resource
	private RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping;

	@Resource
	private IDzqGroupService groupService;
	
	@Resource
	private IConfigService configService;
	
	@Resource
	private ConfigCache configCache;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Set<String> resources = getPermissions();
		groupService.resetPermissions(1L, resources);
		configService.list().forEach(c -> configCache.set(c));
	}

	private Set<String> getPermissions() {
		Set<String> resources = new HashSet<>();
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingInfoHandlerMapping.getHandlerMethods();
		handlerMethods.forEach((info, handlerMethod) -> {
			Permission permission= handlerMethod.getMethod().getAnnotation(Permission.class);
			if(Objects.isNull(permission)) {
				return;
			}
			resources.add(permission.value());
		});
		return resources;
	}

}
