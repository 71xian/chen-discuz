package com.aoexe.discuz.system.modular.user.entity;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value="DenyUser对象", description="")
public class DenyUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long denyUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;


}
