package com.aoexe.discuz.system.modular.config.entity;

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
@ApiModel(value="Config对象", description="")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设置项 key")
    private String configKey;

    @ApiModelProperty(value = "设置项 value")
    private String configValue;

    @ApiModelProperty(value = "设置项 tag")
    private String configTag;


}
