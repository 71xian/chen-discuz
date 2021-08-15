package cn.chenyuxian.discuz.system.modular.sequence.entity;

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
public class Sequences extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 内容分类ID
     */
    private String categoryIds;

    /**
     * 用户角色ID
     */
    private String groupIds;

    /**
     * 用户ID
     */
    private String userIds;

    /**
     * 话题ID
     */
    private String topicIds;

    /**
     * 主题ID/帖子
     */
    private String threadIds;

    /**
     * 阻止显示的用户ID
     */
    private String blockUserIds;

    /**
     * 阻止显示的话题ID
     */
    private String blockTopicIds;

    /**
     * 阻止显示的主题ID/帖子
     */
    private String blockThreadIds;


}
