package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 主题视频转码状态枚举
 *
 * @author chenyuxian
 * @date 2021-08-13
 */
@Getter
public enum ThreadVideoStatusEnum {

	/**
	 * 转码中
	 */
	TRANSCODING(0),
	
	/**
	 * 转码完成
	 */
	SUCCESS(1),
	
	/**
	 * 转码失败
	 */
	FAIL(2);
	
	private final Integer key;

	private ThreadVideoStatusEnum(Integer key) {
		this.key = key;
	}
	
	
}
