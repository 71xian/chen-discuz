package com.aoexe.discuz.system.modular.post.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class PostReward implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 悬赏帖ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联的post主键ID
     */
    private Long postId;

    /**
     * 关联的reply主键ID
     */
    private Long replyId;

    /**
     * 0为所有人回答，1为指定人回答
     */
    private Integer type;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 被指定人ID，可为空
     */
    private Long answerId;

    /**
     * 悬赏金额
     */
    private Long money;

    /**
     * 剩余的悬赏金额
     */
    private Long remainMoney;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 过期时间
     */
    private LocalDateTime expiredAt;


}
