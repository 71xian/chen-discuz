package cn.chenyuxian.discuz.system.modular.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

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
 * @since 2021-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UserActionLog对象", description="")
public class UserActionLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "操作用户 id")
    private Long userId;

    @ApiModelProperty(value = "操作")
    private String action;

    @ApiModelProperty(value = "备注")
    private String message;

    @ApiModelProperty(value = "模型 id")
    private Integer logAbleId;

    @ApiModelProperty(value = "模型")
    private String logAbleType;


}
