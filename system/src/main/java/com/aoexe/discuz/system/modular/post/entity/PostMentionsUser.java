package com.aoexe.discuz.system.modular.post.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value="PostMentionsUser对象", description="")
public class PostMentionsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long postId;

    private Long mentionsUserId;


}
