package com.aoexe.discuz.system.modular.category.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Category对象", description="")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类描述")
    private String description;

    @ApiModelProperty(value = "分类图标")
    private String icon;

    @ApiModelProperty(value = "显示顺序")
    private Integer sort;

    @ApiModelProperty(value = "属性：0 正常 1 首页展示")
    private Integer property;

    @ApiModelProperty(value = "主题数")
    private Integer threadCount;

    @ApiModelProperty(value = "分类版主")
    private String moderators;

    @ApiModelProperty(value = "ip 地址")
    private String ip;

    @ApiModelProperty(value = "所属一级分类的ID")
    private Long parentid;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;


}
