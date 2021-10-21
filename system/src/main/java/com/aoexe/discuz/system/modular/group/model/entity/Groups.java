package com.aoexe.discuz.system.modular.group.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
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
@TableName("`groups`")
public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户组名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 颜色
     */
    private String color;

    /**
     * icon
     */
    private String icon;

    /**
     * 是否默认
     */
    private Integer isDefault;

    /**
     * 是否显示在用户名后
     */
    private Integer isDisplay;

    /**
     * 是否收费：0不收费，1收费
     */
    private Integer isPaid;

    /**
     * 收费金额
     */
    private Long fee;

    /**
     * 付费获得天数
     */
    private Long days;

    /**
     * 分成比例
     */
    private Double scale;

    /**
     * 是否可以 推广下线
     */
    private Integer isSubordinate;

    /**
     * 是否可以 收入提成
     */
    private Integer isCommission;


}
