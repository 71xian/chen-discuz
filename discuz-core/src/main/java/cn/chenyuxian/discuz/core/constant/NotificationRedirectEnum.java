package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 通知跳转类型枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum NotificationRedirectEnum {

	/**
	 * 无跳转
	 */
	TO_NO(0),
	
	/**
	 * 跳转H5
	 */
	TO_H5(1),
	
	/**
	 * 跳转小程序
	 */
	TO_MINIPROGRAM(2);
	
	private final Integer key;

	private NotificationRedirectEnum(Integer key) {
		this.key = key;
	}
}
