package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 订单状态枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum OrderStatusEnum {

	/**
	 * 待付款
	 */
	PENDING(0),

	/**
	 * 已付款
	 */
	PAID(1),

	/**
	 * 取消订单
	 */
	CANCEL(2),

	/**
	 * 支付失败
	 */
	FAILED(3),

	/**
	 * 订单已过期
	 */
	EXPIRED(4),

	/**
	 * 部分退款
	 */
	PART_OF_RETURN(5),

	/**
	 * 全额退款
	 */
	RETURN(10),

	/**
	 * 在异常订单处理中不进行处理的订单
	 */
	UNTREATED(11);

	private final Integer key;

	private OrderStatusEnum(Integer key) {
		this.key = key;
	}
}
