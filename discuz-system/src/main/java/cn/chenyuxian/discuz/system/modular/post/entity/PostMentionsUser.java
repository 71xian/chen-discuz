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
public class PostMentionsUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long postId;

    private Long mentionsUserId;


}
