package com.aoexe.discuz.system.modular.user.entity;

import java.math.BigDecimal;
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
@ApiModel(value="UserWallets对象", description="")
public class UserWallets implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "钱包所属人 id")
    private Long userId;

    @ApiModelProperty(value = "可用金额")
    private BigDecimal availableAmount;

    @ApiModelProperty(value = "冻结金额")
    private BigDecimal freezeAmount;

    @ApiModelProperty(value = "钱包状态:0正常，1冻结提现")
    private Integer walletStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;


}
