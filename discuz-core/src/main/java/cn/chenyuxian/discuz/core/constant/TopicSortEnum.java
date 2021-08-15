package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 话题排序规则枚举
 *
 * @author chenyuxian
 * @date 2021-08-13
 */
@Getter
public enum TopicSortEnum {

	/**
	 * 按照热度排序
	 */
	VIEWCOUNT(1),
	
	/**
	 * 按内容数排序
	 */
	THREADCOUNT(2),
	
	/**
	 * 推荐话题
	 */
	RECOMMENDED(1);
	
	private final Integer key;

	private TopicSortEnum(Integer key) {
		this.key = key;
	}
	
	
}
