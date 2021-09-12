package com.aoexe.discuz.system.modular.group.model.entity;

import java.io.Serializable;
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
public class GroupPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组 id
     */
    private Long groupId;

    /**
     * 权限名称
     */
    private String permission;


}
