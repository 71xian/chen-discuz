package com.aoexe.discuz.system.modular.order.entity;

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
@ApiModel(value="PayNotify对象", description="")
public class PayNotify implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "支付通知 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "支付编号")
    private String paymentSn;

    @ApiModelProperty(value = "付款人 id")
    private Long userId;

    @ApiModelProperty(value = "商户平台交易号")
    private String tradeNo;

    @ApiModelProperty(value = "0未接受到通知，1收到通知")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;


}
