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
@ApiModel(value="Questions对象", description="")
public class Questions implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "主题 id")
    private Long threadId;

    @ApiModelProperty(value = "提问人用户 id")
    private Long userId;

    @ApiModelProperty(value = "被提问的用户 id")
    private Long beUserId;

    @ApiModelProperty(value = "回答内容")
    private String content;

    @ApiModelProperty(value = "回答人 ip 地址")
    private String ip;

    @ApiModelProperty(value = "回答人端口")
    private Integer port;

    @ApiModelProperty(value = "问答价格")
    private BigDecimal price;

    @ApiModelProperty(value = "围观单价")
    private BigDecimal onlookerUnitPrice;

    @ApiModelProperty(value = "当前围观总价格")
    private BigDecimal onlookerPrice;

    @ApiModelProperty(value = "当前围观总人数")
    private Long onlookerNumber;

    @ApiModelProperty(value = "是否允许围观")
    private Integer isOnlooker;

    @ApiModelProperty(value = "是否已回答 0未回答 1已回答 2已过期")
    private Integer isAnswer;

    @ApiModelProperty(value = "回答内容是否合法")
    private Integer isApproved;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expiredAt;

    @ApiModelProperty(value = "回答时间")
    private LocalDateTime answeredAt;


}
