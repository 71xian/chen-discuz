package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 用户组类型枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum GroupEnum {

	/**
	 * 管理员用户组标识
	 */
	ADMINISTRATOR_ID(1),
	
	/**
	 * 禁用用户组标识
	 */
	BAN_ID(5),
	
	/**
	 * 待付费用户组标识
	 */
	UNPAID(6),
	
	/**
	 * 游客用户组标识
	 */
	GUEST_ID(7),
	
	/**
	 * 普通会员用户组标识
	 */
	MEMBER_ID(10);
	
	private final Integer key;

	private GroupEnum(Integer key) {
		this.key = key;
	}
	
	
}
