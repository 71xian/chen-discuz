package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 主题标签枚举
 *
 * @author chenyuxian
 * @date 2021-08-13
 */
@Getter
public enum ThreadTagEnum {

	/**
	 * 文本
	 */
	TEXT(100),
	
	/**
	 * 图片
	 */
    IMAGE(101),        
    
    /**
     * 语音
     */
    VOICE(102),        
    
    /**
     * 视频
     */
    VIDEO(103),       
    
    /**
     * 商品
     */
    GOODS(104),        
    
    /**
     * 问答
     */
    QA(105),            
    
    /**
     * 红包
     */
    RED_PACKET(106),    
    
    /**
     * 悬赏问答
     */
    REWARD(107),         
    
    /**
     * 文件附件
     */
    DOC(108);            
	
	private final Integer key;

	private ThreadTagEnum(Integer key) {
		this.key = key;
	}
	
	
}
