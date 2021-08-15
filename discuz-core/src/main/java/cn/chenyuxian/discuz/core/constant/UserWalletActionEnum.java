package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 用户钱包操作枚举
 *
 * @author chenyuxian
 * @date 2021-08-13
 */
@Getter
public enum UserWalletActionEnum {

	/**
	 * 增加操作
	 */
	INCREASE(1),

	/**
	 * 减少操作
	 */
	DECREASE(2),

	/**
	 * 增加冻结金额
	 */
	INCREASE_FREEZE(3),

	/**
	 * 减少冻结金额
	 */
	DECREASE_FREEZE(4),

	/**
	 * 冻结金额
	 */
	FREEZE(5),

	/**
	 * 解冻金额
	 */
	UNFREEZE(6);

	private final Integer key;

	private UserWalletActionEnum(Integer key) {
		this.key = key;
	}

}
