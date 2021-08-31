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
@ApiModel(value="ThreadTopic对象", description="")
public class ThreadTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主题ID")
    private Long threadId;

    @ApiModelProperty(value = "话题ID")
    private Long topicId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;


}
