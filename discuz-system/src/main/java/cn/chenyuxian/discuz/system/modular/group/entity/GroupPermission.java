package cn.chenyuxian.discuz.system.modular.group.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GroupPermission extends BaseEntity {

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
