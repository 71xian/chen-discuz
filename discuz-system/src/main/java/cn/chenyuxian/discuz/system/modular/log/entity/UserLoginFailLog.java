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
@ApiModel(value="UserLoginFailLog对象", description="")
public class UserLoginFailLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "ip 地址")
    private String ip;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "错误次数")
    private Integer count;


}
