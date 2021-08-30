package com.aoexe.discuz.system.config;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import com.aoexe.discuz.core.context.constant.ConstantContext;
import com.aoexe.discuz.system.core.cache.ResourceCache;
import com.aoexe.discuz.system.modular.config.entity.Config;
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

	@Autowired
	private IDzqGroupService groupService;
	
	@Autowired
	private IConfigService configService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Set<String> resources = getPermissions();
		ResourceCache.put(resources);
		groupService.editPermission(1L, resources, true);
		List<Config> list = configService.list();
		list.forEach(config -> {
			ConstantContext.putConstant(config.getConfigKey(), config.getConfigValue());
		});
	}

	private Set<String> getPermissions() {
		Set<String> resources = new HashSet<>();
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingInfoHandlerMapping.getHandlerMethods();
		handlerMethods.keySet().forEach(info -> {
			Set<String> methods = info.getPatternsCondition().getPatterns();
			resources.addAll(methods);
		});
		return resources;
	}

}
