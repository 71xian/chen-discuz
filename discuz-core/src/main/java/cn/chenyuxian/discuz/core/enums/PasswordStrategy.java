package cn.chenyuxian.discuz.core.enums;

import lombok.Getter;

/**
 * 密码强度策略
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Getter
public enum PasswordStrategy {

	LOW(""),
	
	/**
	 * 至少8个字符，至少1个大写字母，1个小写字母和1个数字
	 */
	MEDIUM("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,32}$"),
	
	/**
	 * 至少8个字符，至少1个大写字母，1个小写字母，1个数字和1个特殊字符
	 */
	STRONG("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,32}");
	
	private final String value;

	private PasswordStrategy(String value) {
		this.value = value;
	}
	
}
