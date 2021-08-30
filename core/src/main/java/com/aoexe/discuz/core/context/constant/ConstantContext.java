package com.aoexe.discuz.core.context.constant;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 系统参数配置容器
 *
 * @author chenyuxian
 * @date 2021-08-29
 */
public class ConstantContext {

	/**
	 * 保存系统所有的配置
	 */
	private static final Map<String, String> CONSTANT_HOLDER = new HashMap<>();

	public static void putConstant(String key, String value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
			return;
		}
		CONSTANT_HOLDER.put(key, value);
	}

	public static Map<String, String> me() {
		return CONSTANT_HOLDER;
	}
}
