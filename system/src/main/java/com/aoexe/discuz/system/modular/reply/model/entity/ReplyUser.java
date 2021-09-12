package com.aoexe.discuz.system.modular.reply.model.entity;

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
public class ReplyUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论 id
     */
    private Long commentId;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;


}
