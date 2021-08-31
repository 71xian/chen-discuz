package com.aoexe.discuz.system.modular.post.entity;

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
@ApiModel(value="PostGoods对象", description="")
public class PostGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "帖子 id")
    private Long postId;

    @ApiModelProperty(value = "平台商品 id")
    private String platformId;

    @ApiModelProperty(value = "商品标题")
    private String title;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value = "商品封面图")
    private String imagePath;

    @ApiModelProperty(value = "商品来源:0淘宝 1天猫 2京东 等")
    private Integer type;

    @ApiModelProperty(value = "商品状态:0正常 1失效/下架")
    private Integer status;

    @ApiModelProperty(value = "预解析内容")
    private String readyContent;

    @ApiModelProperty(value = "解析详情页地址")
    private String detailContent;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deletedAt;


}
