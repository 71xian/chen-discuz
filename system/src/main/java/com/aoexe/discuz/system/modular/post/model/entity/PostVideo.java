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
public class PostVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 音视频 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 帖子 id
     */
    private Long postId;

    /**
     * 评论id
     */
    private Long replyId;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 类型：0 视频 1 音频
     */
    private Integer type;

    /**
     * 音视频状态：0 转码中 1 转码完成 2 转码失败
     */
    private Integer status;

    /**
     * 转码失败原因
     */
    private String reason;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 媒体文件唯一标识
     */
    private String fileId;

    /**
     * 视频高
     */
    private Integer height;

    /**
     * 视频宽
     */
    private Integer width;

    /**
     * 视频时长
     */
    private Integer duration;

    /**
     * 媒体播放地址
     */
    private String mediaUrl;

    /**
     * 媒体封面地址
     */
    private String coverUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
