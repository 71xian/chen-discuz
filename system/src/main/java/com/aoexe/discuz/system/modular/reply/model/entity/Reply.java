package com.aoexe.discuz.system.modular.reply.model.entity;

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
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发表用户 id
     */
    private Long userId;

    /**
     * 关联帖子 id
     */
    private Long postId;

    /**
     * 回复 id
     */
    private Long replyCommentId;

    /**
     * 回复用户 id
     */
    private Long replyUserId;

    /**
     * 评论回复 id
     */
    private Long commentPostId;

    /**
     * 评论回复用户 id
     */
    private Long commentUserId;

    /**
     * 内容
     */
    private String content;

    /**
     * ip 地址
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 关联回复数
     */
    private Integer replyCount;

    /**
     * 喜欢数
     */
    private Integer likeCount;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    /**
     * 删除用户 id
     */
    private Long deletedUserId;

    /**
     * 是否首个回复
     */
    private Integer isFirst;

    /**
     * 是否是回复回帖的内容
     */
    private Integer isComment;

    /**
     * 是否合法
     */
    private Integer isApproved;


}
