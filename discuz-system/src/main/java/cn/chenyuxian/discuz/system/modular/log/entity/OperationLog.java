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
@ApiModel(value="OperationLog对象", description="")
public class OperationLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "url路径")
    private String path;

    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "ip 地址")
    private String ip;

    @ApiModelProperty(value = "body请求数据")
    private String input;

    @ApiModelProperty(value = "日志类型:0后台操作")
    private Integer type;


}
