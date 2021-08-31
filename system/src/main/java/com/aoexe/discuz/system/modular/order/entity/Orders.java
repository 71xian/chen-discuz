package com.aoexe.discuz.system.modular.order.entity;

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
@ApiModel(value="Orders对象", description="")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "支付编号")
    private String paymentSn;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "站长分成金额")
    private BigDecimal masterAmount;

    @ApiModelProperty(value = "作者分成金额")
    private BigDecimal authorAmount;

    @ApiModelProperty(value = "第三者收益金额")
    private BigDecimal thirdPartyAmount;

    @ApiModelProperty(value = "作者受邀时的分成比例")
    private Double beScale;

    @ApiModelProperty(value = "付款人 id")
    private Long userId;

    @ApiModelProperty(value = "收款人 id")
    private Long payeeId;

    @ApiModelProperty(value = "第三者收益人 id")
    private Long thirdPartyId;

    @ApiModelProperty(value = "交易类型：1注册、2打赏、3付费主题、4付费用户组")
    private Integer type;

    @ApiModelProperty(value = "用户组 id")
    private Long groupId;

    @ApiModelProperty(value = "主题 id")
    private Long threadId;

    @ApiModelProperty(value = "订单状态：0待付款；1已付款；2.取消订单；3支付失败；4订单过期")
    private Integer status;

    @ApiModelProperty(value = "付款方式：微信（10：pc扫码，11：h5支付，12：微信内支付")
    private Integer paymentType;

    @ApiModelProperty(value = "是否匿名(0否1是)")
    private Integer isAnonymous;

    @ApiModelProperty(value = "关联的posts主键ID")
    private Long postId;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refund;

    @ApiModelProperty(value = "付费注册过期时长")
    private LocalDateTime expiredAt;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "退款时间")
    private LocalDateTime returnAt;


}
