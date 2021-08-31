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
@ApiModel(value="ThreadRewards对象", description="")
public class ThreadRewards implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "悬赏帖ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "关联的threads主键ID")
    private Long threadId;

    @ApiModelProperty(value = "关联的posts主键ID")
    private Long postId;

    @ApiModelProperty(value = "0为所有人回答，1为指定人回答")
    private Integer type;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "被指定人ID，可为空")
    private Long answerId;

    @ApiModelProperty(value = "悬赏金额")
    private BigDecimal money;

    @ApiModelProperty(value = "剩余的悬赏金额")
    private BigDecimal remainMoney;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expiredAt;


}
