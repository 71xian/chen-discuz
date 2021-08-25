package cn.chenyuxian.discuz.system.modular.setting.entity;

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
 * @since 2021-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="Settings对象", description="")
public class Settings extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设置项 key")
    private String key;

    @ApiModelProperty(value = "设置项 value")
    private String value;

    @ApiModelProperty(value = "设置项 tag")
    private String tag;


}
