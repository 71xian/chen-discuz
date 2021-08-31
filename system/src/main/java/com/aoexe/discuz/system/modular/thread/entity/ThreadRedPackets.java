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
@ApiModel(value="ThreadRedPackets对象", description="")
public class ThreadRedPackets implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "红包ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "关联的threads主键ID")
    private Long threadId;

    @ApiModelProperty(value = "关联的posts主键ID")
    private Long postId;

    @ApiModelProperty(value = "发放规则，0定额，1随机")
    private Integer rule;

    @ApiModelProperty(value = "领取红包条件，0回复，1集赞")
    private Integer condition;

    @ApiModelProperty(value = "若红包领取条件为集赞，必填集赞数")
    private Integer likenum;

    @ApiModelProperty(value = "红包总金额")
    private BigDecimal money;

    @ApiModelProperty(value = "红包个数")
    private Integer number;

    @ApiModelProperty(value = "剩余红包总额")
    private BigDecimal remainMoney;

    @ApiModelProperty(value = "剩余红包个数")
    private Integer remainNumber;

    @ApiModelProperty(value = "0:红包已过期,1:红包未过期")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;


}
