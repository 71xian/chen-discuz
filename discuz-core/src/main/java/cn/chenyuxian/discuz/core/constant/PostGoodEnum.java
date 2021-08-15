package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 帖子商品来源枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum PostGoodEnum {

	/**
	 * 淘宝
	 */
	TAOBAO(0,"taobao"),
	
	/**
	 * 天猫
	 */
	TMALL(1,"tmall"),
	
	/**
	 * 京东
	 */
	JD(2,"jd"),
	
	/**
	 * 拼多多H5
	 */
	YANGKEDUO(3,"yangkeduo"),
	
	/**
	 * 有赞
	 */
	YOUZHAN(4,"m.youzan"),
	
	/**
	 * 淘宝口令粘贴值
	 */
	MTAOBAO(5,"m.tb"),
	
	/**
	 * 京东粘贴值H5域名
	 */
	MJD(6,"m.jd"),
	
	/**
	 * 有赞粘贴值
	 */
	MYOUZHAN(7,"youzan");
	
	private final Integer key;
	
	private final String value;

	private PostGoodEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
}
