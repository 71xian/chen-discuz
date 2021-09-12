package com.aoexe.discuz.system.modular.config.model.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设置项 key
     */
    private String configKey;

    /**
     * 设置项 value
     */
    private String configValue;

    /**
     * 设置项 tag
     */
    private String configTag;


}
