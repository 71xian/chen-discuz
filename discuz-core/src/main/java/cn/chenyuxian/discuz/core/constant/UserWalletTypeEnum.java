package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 钱包明细类型枚举
 *
 * @author chenyuxian
 * @date 2021-08-13
 */
@Getter
public enum UserWalletTypeEnum {

	/**
	 * 问答冻结
	 */
    QUESTION_FREEZE(8),

    /**
     * 问答返还解冻
     */
    QUESTION_RETURN_THAW(9), 

    /**
     * 提现冻结
     */
    CASH_FREEZE(10), 

    /**
     * 提现成功
     */
    CASH_SUCCESS(11), 

    /**
     * 提现解冻
     */
    CASH_THAW(12), 

    /**
     * 注册收入
     */
    INCOME_REGISTER(30), 

    /**
     * 注册分成收入
     */
    INCOME_SCALE_REGISTER(34), 

    /**
     * 打赏收入
     */
    INCOME_REWARD(31), 

    /**
     * 人工收入
     */
    INCOME_ARTIFICIAL(32), 

    /**
     * 分成打赏收入
     */
    INCOME_SCALE_REWARD(33), 

    /**
     * 问答答题收入
     */
    INCOME_QUESTION_REWARD(35), 

    /**
     * 问答围观收入
     */
    INCOME_ONLOOKER_REWARD(36), 

    /**
     * 人工支出
     */
    EXPEND_ARTIFICIAL(50), 

    /**
     * 加入用户组支出
     */
    EXPEND_GROUP(51), 

    /**
     * 付费附件支出
     */
    EXPEND_ATTACHMENT(52), 

    /**
     * 打赏支出
     */
    EXPEND_REWARD(41), 

    /**
     * 付费主题收入
     */
    INCOME_THREAD(60), 

    /**
     * 付费主题支出
     */
    EXPEND_THREAD(61), 

    /**
     * 分成付费主题收入
     */
    INCOME_SCALE_THREAD(62), 

    /**
     * 付费附件收入
     */
    INCOME_ATTACHMENT(63), 

    /**
     * 付费附件分成收入
     */
    INCOME_SCALE_ATTACHMENT(64), 

    /**
     * 站点续费支出
     */
    EXPEND_RENEW(71), 

    /**
     * 问答提现支出
     */
    EXPEND_QUESTION(81), 

    /**
     * 问答围观支出
     */
    EXPEND_ONLOOKER(82), 

    

    /**
     * 文字贴红包支出
     */
    EXPEND_TEXT(100), 

    /**
     * 文字贴红包冻结
     */
    TEXT_FREEZE(101), 

    /**
     * 文字贴红包收入
     */
    INCOME_TEXT(102), 

    /**
     * 文字帖冻结返还
     */
    TEXT_RETURN_THAW(103), 

    /**
     * 文字帖订单异常返现
     */
    TEXT_ABNORMAL_REFUND(104), 

    /**
     * 长文帖红包支出
     */
    EXPEND_LONG(110), 

    /**
     * 长文帖红包冻结
     */
    LONG_FREEZE(111), 

    /**
     * 长文帖红包收入
     */
    INCOME_LONG(112), 

    /**
     * 长文帖冻结返还
     */
    LONG_RETURN_THAW(113), 

    /**
     * 长文帖订单异常返现
     */
    LONG_ABNORMAL_REFUND(114), 

    /**
     * 悬赏问答收入
     */
    INCOME_THREAD_REWARD(120), 

    /**
     * 悬赏帖过期-悬赏帖剩余悬赏金额返回
     */
    INCOME_THREAD_REWARD_RETURN(121), 

    /**
     * 悬赏帖过期-悬赏帖剩余悬赏金额平分
     */
    INCOME_THREAD_REWARD_DIVIDE(122), 

    /**
     * 悬赏帖过期-悬赏帖剩余悬赏金额按点赞数分配
     */
    INCOME_THREAD_REWARD_DISTRIBUTION(123), 

    /**
     * 问答帖订单异常返现
     */
    QUESTION_ABNORMAL_REFUND(124), 

    /**
     * 异常订单退款
     */
    ABNORMAL_ORDER_REFUND(130), 

    /**
     * 红包冻结
     */
    REDPACKET_FREEZE(150), 

    /**
     * 红包收入
     */
    REDPACKET_INCOME(151), 

    /**
     * 红包退款
     */
    REDPACKET_REFUND(152), 

    /**
     * 红包支出
     */
    REDPACKET_EXPEND(153), 

    /**
     * 红包订单异常退款
     */
    REDPACKET_ORDER_ABNORMAL_REFUND(154), 

    /**
     * 悬赏问答冻结
     */
    QUESTION_REWARD_FREEZE(160), 

    /**
     * 悬赏问答收入
     */
    QUESTION_REWARD_INCOME(161), 

    /**
     * 悬赏问答退款
     */
    QUESTION_REWARD_REFUND(162), 

    /**
     *  悬赏订单异常退款
     */
    QUESTION_ORDER_ABNORMAL_REFUND(163), 

    /**
     * 悬赏采纳支出
     */
    QUESTION_REWARD_EXPEND(164), 

    /**
     * 悬赏冻结返回
     */
    QUESTION_REWARD_FREEZE_RETURN(165), 

    /**
     * 合并订单冻结
     */
    MERGE_FREEZE(170), 

    /**
     * 合并订单退款
     */
    MERGE_REFUND(171), 

    /**
     * 合并订单异常退款
     */
    MERGE_ORDER_ABNORMAL_REFUND(172); 
	
	private final Integer key;

	private UserWalletTypeEnum(Integer key) {
		this.key = key;
	}
	
	
}
