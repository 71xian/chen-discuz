package com.aoexe.discuz.system.modular.group.entity;

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
 * @since 2021-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="GroupUser对象", description="")
public class GroupUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户组 id")
    private Long groupId;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "用户组到期时间")
    private LocalDateTime expirationTime;


}
