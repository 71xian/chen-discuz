package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 附件类型枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum AttachmentEnum {

	/**
	 * 附件
	 */
	FILE(0, "file"),

	/**
	 * 图片
	 */
	IMAGE(1, "img"),

	/**
	 * 音频
	 */
	AUDIO(2, "audio"),

	/**
	 * 视频
	 */
	VIDEO(3, "video"),

	/**
	 * 消息图片
	 */
	DIALOG_MESSAGE(4, "dialogMessage"),

	/**
	 * 回答图片
	 */
	ANSWER(5, "answer");

	private final Integer key;

	private final String value;

	private AttachmentEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

}
