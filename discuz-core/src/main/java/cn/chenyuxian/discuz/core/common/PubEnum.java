package cn.chenyuxian.discuz.core.common;

import lombok.Getter;

/**
 * 设备来源枚举类
 *
 * @author chenyuxian
 * @date 2021-08-10
 */
@Getter
public enum PubEnum {

	/**
	 * PC端
	 */
	PC(1),
	
	/**
	 * 手机端
	 */
	H5(2),
	
	/**
	 * 小程序端
	 */
	MINPROGRAM(3);
	
	private final Integer code;

	private PubEnum(Integer code) {
		this.code = code;
	}
	
}
