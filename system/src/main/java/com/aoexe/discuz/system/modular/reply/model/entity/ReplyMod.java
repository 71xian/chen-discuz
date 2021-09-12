package com.aoexe.discuz.system.modular.reply.model.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReplyMod implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论 id
     */
    private Long commentId;

    /**
     * 触发的敏感词，半角逗号隔开
     */
    private String stopWord;


}
