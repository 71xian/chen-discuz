package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 注册字段枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum AdminSignInFieldEnum {

	/**
	 * 单行文本框
	 */
	SINGLE_LINE(0,"单行文本框"),
	
	/**
	 * 多行文本框
	 */
	MULTI_LINE(1,"多行文本框"),
	
	/**
	 * 单选
	 */
	RADIO(2,"单选"),
	
	/**
	 * 复选
	 */
	CHECKBOX(3,"复选"),
	
	/**
	 * 图片上传
	 */
	IMAGE_UPLOAD(4,"图片上传"),
	
	/**
	 * 附件上传
	 */
	ZIP_UPLOAD(5,"附件上传");

	private final Integer key;
	
	private final String value;

	private AdminSignInFieldEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
}
