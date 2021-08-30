package com.aoexe.discuz.core.context.constant;

import org.springframework.util.StringUtils;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;

public class ConstantContextHolder {

	public static Integer getPasswordLength() {
		return getSysConfig("password_length");
	}
	
	public static Integer getPasswordStrength() {
		return getSysConfig("password_strength");
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T getSysConfig(String key) {
		String value = ConstantContext.me().get(key);
		if(StringUtils.isEmpty(value)) {
			throw new BaseException(ResponseEnum.RESOURCE_NOT_FOUND);
		}
		try {
			return (T) Integer.valueOf(value);
		} catch (Exception e) {
			return (T) value;
		}
	}
	
}
