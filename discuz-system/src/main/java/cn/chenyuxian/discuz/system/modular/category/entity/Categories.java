package cn.chenyuxian.discuz.system.modular.category.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Categories extends BaseEntity {

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
    private Long parentid;


}
