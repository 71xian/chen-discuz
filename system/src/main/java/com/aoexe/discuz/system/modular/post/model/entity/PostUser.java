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
public class PostUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子 id
     */
    private Long postId;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;


}
