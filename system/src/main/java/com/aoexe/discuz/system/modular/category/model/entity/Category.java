package com.aoexe.discuz.system.modular.category.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.List;
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
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 属性：0 正常 1 首页展示
     */
    private Integer property;

    /**
     * 主题数
     */
    private Integer threadCount;

    /**
     * 分类版主
     */
    private String moderators;

    /**
     * ip 地址
     */
    private String ip;

    /**
     * 所属一级分类的ID
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    List<Category> children;

}
