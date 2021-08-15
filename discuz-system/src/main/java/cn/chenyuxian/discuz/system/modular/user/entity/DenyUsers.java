package cn.chenyuxian.discuz.system.modular.user.entity;

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
public class DenyUsers extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long denyUserId;


}
