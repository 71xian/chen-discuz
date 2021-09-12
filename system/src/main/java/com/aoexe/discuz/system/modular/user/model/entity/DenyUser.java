package com.aoexe.discuz.system.modular.user.model.entity;

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
public class DenyUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long denyUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;


}
