package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 财务统计表统计方式枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum FinanceEnum {

	/**
	 * 统计方式（日）
	 */
	DAYS(1),
	
	/**
	 * 统计方式（周）
	 */
	WEEKS(2),
	
	/**
	 * 统计方式（月）
	 */
	MONTH(3);
	
	private final Integer key;

	private FinanceEnum(Integer key) {
		this.key = key;
	}
	
	
	
}
