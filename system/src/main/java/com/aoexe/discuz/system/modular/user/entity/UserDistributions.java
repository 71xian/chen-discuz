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
@ApiModel(value="UserDistributions对象", description="")
public class UserDistributions implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父级id")
    private Long pid;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "受邀时的分成比例")
    private Double beScale;

    @ApiModelProperty(value = "当前用户所处深度")
    private Integer level;

    @ApiModelProperty(value = "是否可以 推广下线")
    private Integer isSubordinate;

    @ApiModelProperty(value = "是否可以 收入提成")
    private Integer isCommission;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;


}
