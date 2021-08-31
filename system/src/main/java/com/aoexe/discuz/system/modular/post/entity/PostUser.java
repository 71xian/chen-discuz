package com.aoexe.discuz.system.modular.post.entity;

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
@ApiModel(value="PostUser对象", description="")
public class PostUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "帖子 id")
    private Long postId;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;


}
