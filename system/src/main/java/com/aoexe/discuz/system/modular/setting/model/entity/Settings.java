package com.aoexe.discuz.system.modular.setting.model.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-10-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Settings implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设置项 key
     */
    @TableField("`key`")
    private String key;

    /**
     * 设置项 value
     */
    @TableField("`value`")
    private String value;

    /**
     * 设置项 tag
     */
    private String tag;


}
