package com.aoexe.discuz.system.modular.user.entity;

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
@ApiModel(value="UserWalletCash对象", description="")
public class UserWalletCash implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "提现 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "提现用户 id")
    private Long userId;

    @ApiModelProperty(value = "提现交易编号")
    private Long cashSn;

    @ApiModelProperty(value = "提现手续费")
    private BigDecimal cashCharge;

    @ApiModelProperty(value = "实际提现金额")
    private BigDecimal cashActualAmount;

    @ApiModelProperty(value = "提现申请金额")
    private BigDecimal cashApplyAmount;

    @ApiModelProperty(value = "提现状态：1：待审核，2：审核通过，3：审核不通过，4：待打款， 5，已打款， 6：打款失败")
    private Integer cashStatus;

    @ApiModelProperty(value = "提现到账手机号码")
    private String cashMobile;

    @ApiModelProperty(value = "提现转账类型：0：人工转账， 1：企业零钱付款")
    private Integer cashType;

    @ApiModelProperty(value = "备注或原因")
    private String remark;

    @ApiModelProperty(value = "交易时间")
    private LocalDateTime tradeTime;

    @ApiModelProperty(value = "交易号")
    private String tradeNo;

    @ApiModelProperty(value = "错误代码")
    private String errorCode;

    @ApiModelProperty(value = "交易失败描叙")
    private String errorMessage;

    @ApiModelProperty(value = "返款状态，0未返款，1已返款")
    private Integer refundsStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;


}
