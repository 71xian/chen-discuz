package cn.chenyuxian.discuz.system.modular.thread.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ThreadTopic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主题ID
     */
    private Long threadId;

    /**
     * 话题ID
     */
    private Long topicId;


}
