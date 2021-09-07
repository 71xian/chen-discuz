package com.aoexe.discuz.system.core.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.aoexe.discuz.system.modular.group.consts.Constant;
import com.aoexe.discuz.system.modular.group.entity.DzqGroup;

/**
 * 缓存用户组信息
 *
 * @author chenyuxian
 * @date 2021-08-30
 */
@Component
public class GroupCache {

	// 使用字符串作为key，而不是使用Long作为key
	// 这样子可以放一个"DEFAULT"键进去，设置默认用户组
	private static final Map<String, DzqGroup> GROUP_MAPPING = new ConcurrentHashMap<>();

	public DzqGroup getDefaultGroup() {
		return GROUP_MAPPING.get(Constant.DEFAULT_GROUP);
	}

	public DzqGroup get(String key) {
		return GROUP_MAPPING.get(key);
	}

	public void put(String key, DzqGroup group) {
		GROUP_MAPPING.put(key, group);
	}
	
	public void remove(String key) {
		GROUP_MAPPING.remove(key);
	}
}
