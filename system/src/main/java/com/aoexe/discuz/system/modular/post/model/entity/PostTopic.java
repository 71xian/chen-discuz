package com.aoexe.discuz.system.modular.post.model.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PostTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子ID
     */
    private Long postId;

    /**
     * 话题ID
     */
    private Long topicId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;


}
