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
public class ReplyMentionUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long commentId;

    private Long mentionsUserId;


}
