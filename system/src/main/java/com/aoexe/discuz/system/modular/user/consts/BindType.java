package com.aoexe.discuz.system.modular.user.consts;

import lombok.Getter;

/**
 * 登录绑定类型
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Getter
public enum BindType {

	/**
	 * 默认或微信
	 */
	DEFAULT(0),
	
	/**
	 * QQ
	 */
	QQ(2);
	
	private final Integer type;

	private BindType(Integer type) {
		this.type = type;
	}
	
}
