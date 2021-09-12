package com.aoexe.discuz.system.modular.user.model.enums;

import java.util.HashMap;
import java.util.Map;

public class StatusMap {

	private static final Map<Integer, String> map = new HashMap<>();
	
	static {
		map.put(0, "正常");
		map.put(1, "禁用");
		map.put(2, "审核");
		map.put(3, "审核不通过");
		map.put(4, "审核忽略");
		map.put(5, "待填写扩展信息");
	}
	
	public static String get(Integer key) {
		return map.get(key);
	}
}
