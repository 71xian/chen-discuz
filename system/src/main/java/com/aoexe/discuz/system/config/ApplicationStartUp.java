package com.aoexe.discuz.system.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import com.aoexe.discuz.core.annotion.Permission;
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

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<String> resources = getPermissions();
		groupService.editPermission(1L, resources, true);
	}

	private List<String> getPermissions() {
		List<String> resources = new ArrayList<>();
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingInfoHandlerMapping.getHandlerMethods();
		handlerMethods.forEach((info, handlerMethod) -> {
			if (handlerMethod.getBeanType().getAnnotation(Permission.class) == null
					&& handlerMethod.getMethod().getAnnotation(Permission.class) == null) {
				return;
			}
			Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
			String path = methods.toArray()[0] + ":" + info.getPatternsCondition().getPatterns().toArray()[0];
			resources.add(path);
		});
		return resources;
	}

}
