package com.aoexe.discuz.system.modular.user.entity;

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
@ApiModel(value="UserWalletFailLogs对象", description="")
public class UserWalletFailLogs implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "ip 地址")
    private String ip;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;


}
