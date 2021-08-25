package cn.chenyuxian.discuz.core.context.resource;

import java.util.HashSet;
import java.util.Set;

/**
 * 存放系统所有权限
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
public class ResourceContext {

	private static final Set<String> RESOURCES = new HashSet<>();

	public static void set(Set<String> resources) {
		RESOURCES.addAll(resources);
	}
	
	public static Set<String> get(){
		return RESOURCES;
	}
	
}
