package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 邀请状态枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum InviteStatusEnum {

	/**
	 * 失效
	 */
	INVALID(0),
	
	/**
	 * 未使用
	 */
	UNUSED(1),
	
	/**
	 * 已使用
	 */
	USED(2),
	
	/**
	 * 已过期
	 */
	EXPIRED(3);
	
	private final Integer key;

	private InviteStatusEnum(Integer key) {
		this.key = key;
	}
}
