package com.aoexe.discuz.system.modular.post.entity;

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
@ApiModel(value="PostMod对象", description="")
public class PostMod implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "帖子 id")
    private Long postId;

    @ApiModelProperty(value = "触发的敏感词，半角逗号隔开")
    private String stopWord;


}
