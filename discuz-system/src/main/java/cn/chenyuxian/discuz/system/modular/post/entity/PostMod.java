package cn.chenyuxian.discuz.system.modular.post.entity;

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
public class PostMod extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子 id
     */
    private Long postId;

    /**
     * 触发的敏感词，半角逗号隔开
     */
    private String stopWord;


}
