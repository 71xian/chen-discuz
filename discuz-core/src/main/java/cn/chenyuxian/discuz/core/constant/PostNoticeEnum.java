package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 通知Post操作方式枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public enum PostNoticeEnum {

	/**
	 * 内容修改
	 */
	NOTIFY_EDIT_CONTENT_TYPE("edit_content"), 
	
	/**
	 * 内容不合法/内容忽略
	 */
	NOTIFY_UNAPPROVED_TYPE("unapproved"), 
	
	/**
	 * 内容合法
	 */
	NOTIFY_APPROVED_TYPE("approved"), 
	
	/**
	 * 内容加精
	 */
	NOTIFY_ESSENCE_TYPE("essence"), 
	
	/**
	 * 内容置顶
	 */
	NOTIFY_STICKY_TYPE("sticky"), 
	
	/**
	 * 内容删除
	 */
	NOTIFY_DELETE_TYPE("delete"); 
	
	private final String key;
	
	private PostNoticeEnum(String key) {
		this.key = key;
	}
}
