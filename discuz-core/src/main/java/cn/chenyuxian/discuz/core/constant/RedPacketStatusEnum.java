package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 红包状态枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum RedPacketStatusEnum {

	/**
	 * 红包已过期(暂时无用)
	 */
	RED_PACKET_STATUS_OVERDUE(0),

	/**
	 * 红包未过期
	 */
	RED_PACKET_STATUS_VALID(1),

	/**
	 * 红包已领完
	 */
	RED_PACKET_STATUS_BROUGHT_OUT(2),

	/**
	 * 红包已退还
	 */
	RED_PACKET_STATUS_RETURN(3),

	/**
	 * 不作处理的异常红包
	 */
	RED_PACKET_STATUS_UNTREATED(4);

	private final Integer key;

	private RedPacketStatusEnum(Integer key) {
		this.key = key;
	}

}
