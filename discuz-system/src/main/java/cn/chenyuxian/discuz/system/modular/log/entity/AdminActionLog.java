package cn.chenyuxian.discuz.system.modular.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import cn.chenyuxian.discuz.system.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="AdminActionLog对象", description="")
public class AdminActionLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "操作日志ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "操作描述")
    private String actionDesc;

    @ApiModelProperty(value = "ip地址")
    private String ip;


}
