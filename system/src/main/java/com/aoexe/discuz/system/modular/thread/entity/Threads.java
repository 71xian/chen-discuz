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
@ApiModel(value="Threads对象", description="")
public class Threads implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主题 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建用户 id")
    private Long userId;

    @ApiModelProperty(value = "最后回复用户 id")
    private Long lastPostedUserId;

    @ApiModelProperty(value = "分类 id")
    private Integer categoryId;

    @ApiModelProperty(value = "类型：0普通 1长文 2视频 3图片")
    private Integer type;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "附件价格")
    private BigDecimal attachmentPrice;

    @ApiModelProperty(value = "免费字数百分比")
    private Double freeWords;

    @ApiModelProperty(value = "回复数")
    private Integer postCount;

    @ApiModelProperty(value = "查看数")
    private Integer viewCount;

    @ApiModelProperty(value = "打赏数")
    private Integer rewardedCount;

    @ApiModelProperty(value = "付费数")
    private Integer paidCount;

    @ApiModelProperty(value = "分享数")
    private Integer shareCount;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "位置")
    private String location;

    @ApiModelProperty(value = "最新评论时间")
    private LocalDateTime postedAt;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deletedAt;

    @ApiModelProperty(value = "删除用户 id")
    private Long deletedUserId;

    @ApiModelProperty(value = "是否合法")
    private Integer isApproved;

    @ApiModelProperty(value = "是否置顶")
    private Integer isSticky;

    @ApiModelProperty(value = "是否加精")
    private Integer isEssence;

    @ApiModelProperty(value = "是否推荐到首页（0否 1是）")
    private Integer isSite;

    @ApiModelProperty(value = "是否匿名 0否 1是")
    private Integer isAnonymous;

    @ApiModelProperty(value = "是否显示 0否 1是")
    private Integer isDisplay;

    @ApiModelProperty(value = "是否添加红包，0未添加，1添加")
    private Integer isRedPacket;

    @ApiModelProperty(value = "是否为草稿，0不是，1是")
    private Integer isDraft;


}
