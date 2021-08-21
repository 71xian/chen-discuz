package cn.chenyuxian.discuz.core.enums;

import lombok.Getter;

/**
 * 用户状态
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum UserStatus {

	/**
	 * 正常
	 */
	NORMAL(0),

	/**
	 * 禁用
	 */
	BAN(1),

	/**
	 * 审核中
	 */
	MOD(2),

	/**
	 * 审核不通过
	 */
	REFUSE(3),

	/**
	 * 审核忽略
	 */
	IGNORE(4),

	/**
	 * 待填写注册扩展信息
	 */
	NEED_FIELDS(10);

	private final Integer status;

	private UserStatus(Integer status) {
		this.status = status;
	}

}
