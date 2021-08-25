package cn.chenyuxian.discuz.system.modular.group.entity;

import java.time.LocalDateTime;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
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
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="GroupUser对象", description="")
public class GroupUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户组 id")
    private Long groupId;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "用户组到期时间")
    private LocalDateTime expirationTime;


}
