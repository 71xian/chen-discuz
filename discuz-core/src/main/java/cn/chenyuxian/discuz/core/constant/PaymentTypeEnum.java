package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 付款方式枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum PaymentTypeEnum {

	/**
	 * 微信扫码支付
	 */
	WECHAT_NATIVE(10), 

	/**
	 * 微信h5支付
	 */
    WECHAT_WAP(11), 

    /**
     * 微信网页、公众号
     */
    WECHAT_JS(12), 

    /**
     * 微信小程序支付
     */
    WECHAT_MINI(13), 

    /**
     * 钱包支付
     */
    WALLET(20);
	
	private final Integer key;
	
	private PaymentTypeEnum(Integer key) {
		this.key = key;
	}
}
