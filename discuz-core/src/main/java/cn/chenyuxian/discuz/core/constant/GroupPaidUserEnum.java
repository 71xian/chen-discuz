package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 用户组删除方式枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum GroupPaidUserEnum {

	/**
	 * 到期删除
	 */
	DELETE_TYPE_EXPIRE(1),
	
	/**
	 * 管理员删除
	 */
	DELETE_TYPE_ADMIN(2),
	
	/**
	 * 用户复购
	 */
	DELETE_TYPE_RENEW(3);
	
	private final Integer key;
	
	private GroupPaidUserEnum(Integer key) {
		this.key = key;
	}
}
