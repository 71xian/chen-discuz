package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 敏感词处理方式枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum StopWordEnum {

	/**
     * 忽略、不处理
     */
    IGNORE("{IGNORE}"),

    /**
     * 审核
     */
    MOD("{MOD}"),

    /**
     * 禁用
     */
    BANNED("{BANNED}"),

    /**
     * 替换
     */
    REPLACE("{REPLACE}");
	
	private final String key;
	
	private StopWordEnum(String key) {
		this.key = key;
	}
}
