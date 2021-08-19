package cn.chenyuxian.discuz.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 红包分配算法
 *
 * @author chenyuxian
 * @date 2021-08-14
 */
public class CouponUtil {

	/**
	 * 红包金额
	 */
	public int amount;

	/**
	 * 红包个数
	 */
	public int num;

	/**
	 * 领取的红包最小金额
	 */
	public int coupon_min;

	/**
	 * 红包分配结果
	 */
	public List<Integer> items = new ArrayList<>();

	public CouponUtil(int amount, int num, int coupon_min) {
		super();
		this.amount = amount * 100;
		this.num = num;
		this.coupon_min = coupon_min <= 1 ? 1 : coupon_min;
		init();
	}

	/**
	 * 红包分配初始化处理
	 *
	 * @author chenyuxian
	 * @date 2021-08-14
	 */
	public void init() {
		int validAmount = this.coupon_min * this.num;
		
		// 验证
		if (this.amount < validAmount) {
			throw new RuntimeException("红包金额必须>=" + validAmount + ".元");
		}

		// 分配红包
		this.apportion();
	}

	/**
	 * 分配红包
	 *
	 * @author chenyuxian
	 * @date 2021-08-14
	 */
	public void apportion() {
		int num = this.num;
		int amount = this.amount;
		int coupon_amount;
		while(num >= 1) {
			if(num == 1) {
				coupon_amount = amount;
			}else {
				int avg_amount = this.amount / this.num;
				coupon_amount = this.calcCouponAmount(avg_amount, avg_amount, num);
			}
			this.items.add(coupon_amount);
			amount -= coupon_amount;
			--num;
		}
		Collections.shuffle(items);
	}

	/**
	 * 计算分配的红包金额
	 *
	 * @param avg_amount 每次计算的平均金额
	 * @param amount     剩余可领取的金额
	 * @param num        剩余可领取的红包个数
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-14
	 */
	public int calcCouponAmount(int avg_amount, int amount, int num) {
		// 如果平均金额小于等于最低金额，则直接返回最低金额
		if(avg_amount <= this.coupon_min) {
			return this.coupon_min;
		}
		
		// 浮动计算
		int coupon_amount = (int)(avg_amount * (1 + this.apportionRandRatio()));
		
		// 如果低于最低金额或超过可领取的最大金额，则重新获取
		if(coupon_amount < this.coupon_min || coupon_amount > this.calcCouponAmountMax(amount, num)) {
			return this.calcCouponAmount(avg_amount, coupon_amount, num);
		}
		return coupon_amount;
	}
	
	/**
	 * 计算分配的红包金额-可领取的最大金额
	 *
	 * @param amount
	 * @param num
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-14
	 */
	public int calcCouponAmountMax(int amount, int num) {
		return this.coupon_min + amount - num * this.coupon_min;
	}
	
	/**
	 * 红包金额浮动比例
	 *
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-14
	 */
	public float apportionRandRatio() {
		Random random = new Random();
		// 60%机率获取剩余平均值的大幅度红包（可能正数、可能负数)
		if ((random.nextInt(100) + 1) <= 60) {
			// 上下幅度70%
			return (random.nextInt(140) - 70) / 100.0F;
		}
		// 其他情况，上下浮动30%
		return (random.nextInt(60) - 30) / 100.0F;
	}
	
}
