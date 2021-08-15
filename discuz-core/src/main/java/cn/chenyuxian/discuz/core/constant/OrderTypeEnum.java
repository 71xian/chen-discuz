package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 订单类型枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum OrderTypeEnum {

	/**
	 * 注册
	 */
	REGISTER(1),

	/**
	 * 打赏
	 */
	REWARD(2),

	/**
	 * 付费主题
	 */
	THREAD(3),

	/**
	 * 付费用户组
	 */
	GROUP(4),

	/**
	 * 问答提问支付
	 */
	QUESTION(5),

	/**
	 * 问答围观
	 */
	ONLOOKER(6),

	/**
	 * 付费附件
	 */
	ATTACHMENT(7),

	/**
	 * 站点续费
	 */
	RENEW(8),

	/**
	 * 红包
	 */
	REDPACKET(9),

	/**
	 * 悬赏
	 */
	QUESTION_REWARD(10),

	/**
	 * 合并订单
	 */
	MERGE(11),

	/**
	 * 文字贴红包
	 */
	TEXT(20),

	/**
	 * 长文帖红包
	 */
	LONG(21);

	private final Integer key;

	private OrderTypeEnum(Integer key) {
		this.key = key;
	}
}
