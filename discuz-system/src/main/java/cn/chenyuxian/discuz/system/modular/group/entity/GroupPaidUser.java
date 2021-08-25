package cn.chenyuxian.discuz.system.modular.group.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;

import java.time.LocalDateTime;
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
@ApiModel(value="GroupPaidUser对象", description="")
public class GroupPaidUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "所属用户")
    private Long userId;

    @ApiModelProperty(value = "用户组 id")
    private Long groupId;

    @ApiModelProperty(value = "订单 id")
    private Long orderId;

    @ApiModelProperty(value = "操作人")
    private Long operatorId;

    @ApiModelProperty(value = "删除类型：1到期删除，2管理员修改，3用户复购")
    private Integer deleteType;

    @ApiModelProperty(value = "用户组到期时间")
    private LocalDateTime expirationTime;

    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deletedAt;


}
