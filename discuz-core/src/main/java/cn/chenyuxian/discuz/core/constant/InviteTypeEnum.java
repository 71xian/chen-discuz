package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 邀请类型枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum InviteTypeEnum {

	/**
	 * 普通会员邀请
	 */
	TYPE_GENERAL(1),
	
	/**
	 * 管理员邀请
	 */
	TYPE_ADMIN(2);
	
	private final Integer key;

	private InviteTypeEnum(Integer key) {
		this.key = key;
	}
	
	
}
