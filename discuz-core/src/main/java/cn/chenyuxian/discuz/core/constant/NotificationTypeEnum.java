package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 通知类型枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum NotificationTypeEnum {

	/**
	 * 数据库（系统）通知
	 */
	SYSTEM_NOTICE(0),
	
	/**
	 * 微信通知
	 */
	WECHAT_NOTICE(1),
	
	/**
	 * 短信通知
	 */
	SMS_NOTICE(2),
	
	/**
	 * 企业微信通知
	 */
	ENTERPRISE_WECHAT_NOTICE(3),
	
	/**
	 * 小程序通知
	 */
	MINI_PROGRAM_NOTICE(4);
	
	private final Integer key;

	private NotificationTypeEnum(Integer key) {
		this.key = key;
	}
}
