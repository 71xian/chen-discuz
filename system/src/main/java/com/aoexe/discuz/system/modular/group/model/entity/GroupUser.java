package com.aoexe.discuz.system.modular.group.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GroupUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组 id
     */
    private Long groupId;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 用户组到期时间
     */
    private LocalDateTime expirationTime;


}
