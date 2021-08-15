package cn.chenyuxian.discuz.core.common;

import lombok.Getter;

/**
 * 客户端来源枚举
 *
 * @author chenyuxian
 * @date 2021-08-14
 */
@Getter
public enum Platform {

	PC(1),
	
	H5(2),
	
	MINPROGRAM(3);
	
	private final Integer code;

	private Platform(Integer code) {
		this.code = code;
	}
	
	
}
