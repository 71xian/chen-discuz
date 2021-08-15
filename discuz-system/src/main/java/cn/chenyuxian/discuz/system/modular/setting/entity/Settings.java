package cn.chenyuxian.discuz.system.modular.setting.entity;

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
public class Settings extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 设置项 key
     */
    private String key;

    /**
     * 设置项 value
     */
    private String value;

    /**
     * 设置项 tag
     */
    private String tag;


}
