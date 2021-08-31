package com.aoexe.discuz.system.modular.thread.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ThreadVideo对象", description="")
public class ThreadVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "音视频 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "主题 id")
    private Long threadId;

    @ApiModelProperty(value = "帖子 id")
    private Long postId;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "类型：0 视频 1 音频")
    private Integer type;

    @ApiModelProperty(value = "音视频状态：0 转码中 1 转码完成 2 转码失败")
    private Integer status;

    @ApiModelProperty(value = "转码失败原因")
    private String reason;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "媒体文件唯一标识")
    private String fileId;

    @ApiModelProperty(value = "视频高")
    private Integer height;

    @ApiModelProperty(value = "视频宽")
    private Integer width;

    @ApiModelProperty(value = "视频时长")
    private BigDecimal duration;

    @ApiModelProperty(value = "媒体播放地址")
    private String mediaUrl;

    @ApiModelProperty(value = "媒体封面地址")
    private String coverUrl;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;


}
