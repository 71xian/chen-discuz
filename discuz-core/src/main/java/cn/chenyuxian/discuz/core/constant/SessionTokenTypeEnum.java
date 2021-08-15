package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 微信登录方式枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum SessionTokenTypeEnum {

	/**
	 * 微信 PC 扫码登陆
	 */
	WECHAT_PC_LOGIN("wechat_pc_login"),

	/**
	 * 微信 PC 绑定
	 */
    WECHAT_PC_BIND("wechat_pc_bind"),

    /**
     * 微信 PC 换绑
     */
    WECHAT_PC_REBIND("wechat_pc_rebind"),

    /**
     * 微信通知错误
     */
    WECHAT_NOTICE_ERROR("wechat_notice_error"),

    /**
     * 手机浏览器微信登录
     */
    WECHAT_MOBILE_LOGIN("wechat_mobile_login"),

    /**
     * 手机浏览器微信绑定
     */
    WECHAT_MOBILE_BIND("wechat_mobile_bind"),

    /**
     * 过渡阶段微信登录
     */
    WECHAT_TRANSITION_LOGIN("wechat_transition_login");
	
	private final String key;
	
	private SessionTokenTypeEnum(String key) {
		this.key = key;
	}
}
