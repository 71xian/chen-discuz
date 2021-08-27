package com.aoexe.discuz.system.modular.group.entity;

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
@ApiModel(value="GroupPermission对象", description="")
public class GroupPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户组 id")
    private Long groupId;

    @ApiModelProperty(value = "权限名称")
    private String permission;


}
