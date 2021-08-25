package cn.chenyuxian.discuz.system.core.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import cn.chenyuxian.discuz.core.annotion.Permission;
import cn.chenyuxian.discuz.system.core.cache.PermissionCache;
import cn.chenyuxian.discuz.system.modular.group.entity.Group;
import cn.chenyuxian.discuz.system.modular.group.entity.GroupPermission;
import cn.chenyuxian.discuz.system.modular.group.service.IGroupPermissionService;
import cn.chenyuxian.discuz.system.modular.group.service.IGroupService;
import cn.chenyuxian.discuz.system.modular.setting.entity.Settings;
import cn.chenyuxian.discuz.system.modular.setting.service.ISettingsService;

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
	private IGroupPermissionService groupPermissionService;
	
	@Autowired
	private IGroupService groupService;
	
	@Autowired
	private ISettingsService settingsService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<String> resources = getPermissions();
		groupPermissionService.removeById(1L);
		List<GroupPermission> permissios = new ArrayList<>();
		resources.forEach(r -> {
			GroupPermission p = new GroupPermission();
			p.setGroupId(1L);
			p.setPermission(r);
			permissios.add(p);
		});
		groupPermissionService.saveBatch(permissios);
		List<Long> groupIds = groupService.list().stream().map(Group::getId).collect(Collectors.toList());
		for(Long id : groupIds) {
			PermissionCache.putPermissions(id, groupPermissionService.getPermissionByGroupId(id));
		}
		
		for(Settings setting : settingsService.list()) {
			ApplicationConfig.getSettings().put(setting.getKey(), setting.getValue());
		}
	}

	private List<String> getPermissions() {
		List<String> resources = new ArrayList<>();
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingInfoHandlerMapping.getHandlerMethods();
		handlerMethods.forEach((info, handlerMethod) -> {
			Method method = handlerMethod.getMethod();
			if (method.getAnnotation(Permission.class) != null) {
				String className = method.getDeclaringClass().getSimpleName();
				String methodName = method.getName();
				String permission = className + ":" + methodName;
				resources.add(permission);
			}
		});
		return resources;
	}

}
