package com.aoexe.discuz.system.core.cache;

import java.util.HashSet;
import java.util.Set;

/**
 * 保存系统所有的接口
 *
 * @author chenyuxian
 * @date 2021-08-30
 */
public class ResourceCache {

	private static final Set<String> API_CACHE = new HashSet<>();

	public static void put(Set<String> resources) {
		API_CACHE.addAll(resources);
	}

	public static Set<String> get() {
		return API_CACHE;
	}

	public static boolean contains(String uri) {
		return API_CACHE.contains(uri);
	}
}
