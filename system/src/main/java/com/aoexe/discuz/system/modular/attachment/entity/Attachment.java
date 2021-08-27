package com.aoexe.discuz.system.modular.attachment.entity;

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
 * @since 2021-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Attachment对象", description="")
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "附件 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "uuid")
    private String uuid;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "类型数据ID(post_id,dialog_message_id…)")
    private Long typeId;

    @ApiModelProperty(value = "附件排序")
    private Integer order;

    @ApiModelProperty(value = "附件类型(0帖子附件，1帖子图片，2帖子视频，3帖子音频，4消息图片)")
    private Integer type;

    @ApiModelProperty(value = "是否远程附件")
    private Integer isRemote;

    @ApiModelProperty(value = "是否合法")
    private Integer isApproved;

    @ApiModelProperty(value = "文件系统生成的名称")
    private String attachment;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件原名称")
    private String fileName;

    @ApiModelProperty(value = "文件大小")
    private Integer fileSize;

    @ApiModelProperty(value = "宽度")
    private Long fileWidth;

    @ApiModelProperty(value = "高度")
    private Long fileHeight;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    @ApiModelProperty(value = "ip 地址")
    private String ip;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;


}
