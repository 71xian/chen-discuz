package cn.chenyuxian.discuz.system.core.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import cn.chenyuxian.discuz.core.annotion.Permission;
import cn.chenyuxian.discuz.system.modular.group.service.IDzqGroupService;

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
			Method method = handlerMethod.getMethod();
			if (method.getDeclaringClass().getAnnotation(Permission.class) != null
					|| method.getAnnotation(Permission.class) != null) {
				String className = method.getDeclaringClass().getSimpleName();
				String methodName = method.getName();
				String permission = className + ":" + methodName;
				resources.add(permission);
			}
		});
		return resources;
	}

}
