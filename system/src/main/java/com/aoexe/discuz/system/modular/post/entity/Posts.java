package com.aoexe.discuz.system.modular.post.entity;

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
@ApiModel(value="Posts对象", description="")
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "回复 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "发表用户 id")
    private Long userId;

    @ApiModelProperty(value = "关联主题 id")
    private Long threadId;

    @ApiModelProperty(value = "回复 id")
    private Long replyPostId;

    @ApiModelProperty(value = "回复用户 id")
    private Long replyUserId;

    @ApiModelProperty(value = "评论回复 id")
    private Long commentPostId;

    @ApiModelProperty(value = "评论回复用户 id")
    private Long commentUserId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "ip 地址")
    private String ip;

    @ApiModelProperty(value = "端口")
    private Integer port;

    @ApiModelProperty(value = "关联回复数")
    private Integer replyCount;

    @ApiModelProperty(value = "喜欢数")
    private Integer likeCount;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deletedAt;

    @ApiModelProperty(value = "删除用户 id")
    private Long deletedUserId;

    @ApiModelProperty(value = "是否首个回复")
    private Integer isFirst;

    @ApiModelProperty(value = "是否是回复回帖的内容")
    private Integer isComment;

    @ApiModelProperty(value = "是否合法")
    private Integer isApproved;


}
