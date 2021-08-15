package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 用户状态枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum UserStatusEnum {

	/**
	 * 正常
	 */
	NORMAL(0, "正常"),

	/**
	 * 禁用
	 */
	BAN(1, "禁用"),

	/**
	 * 审核中
	 */
	MOD(2, "审核中"),

	/**
	 * 审核不通过
	 */
	REFUSE(3, "审核不通过"),

	/**
	 * 审核忽略
	 */
	IGNORE(4, "审核忽略"),

	/**
	 * 待填写注册扩展信息
	 */
	NEED_FIELDS(10, "待填写注册扩展信息");

	private final Integer key;

	private final String value;

	private UserStatusEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

}
