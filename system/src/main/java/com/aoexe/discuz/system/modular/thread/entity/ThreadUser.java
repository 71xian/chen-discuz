package com.aoexe.discuz.system.modular.thread.entity;

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
@ApiModel(value="ThreadUser对象", description="")
public class ThreadUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主题 id")
    private Long threadId;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;


}
