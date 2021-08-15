package cn.chenyuxian.discuz.system.modular.setting.enums;

import lombok.Getter;

/**
 * 密码类型枚举
 *
 * @author chenyuxian
 * @date 2021-08-14
 */
@Getter
public enum PasswordTypeEnum {

	/**
	 * 密码类型必须有数字
	 */
	DIGIEAL(0), 
	
	/**
	 * 密码类型必须有小写字母
	 */
	LOWER_CASE_LETTERS(1), 
	
	/**
	 * 密码类型必须有符号
	 */
	SYMBOL(2), 
	
	/**
	 * 密码类型必须有大写字母
	 */
	UPPERCASE_LETTER(3); 

	private final Integer key;

	private PasswordTypeEnum(Integer key) {
		this.key = key;
	}

}
