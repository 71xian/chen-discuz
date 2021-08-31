package com.aoexe.discuz.system.modular.thread.entity;

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
@ApiModel(value="ThreadTom对象", description="")
public class ThreadTom implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "帖子id")
    private Long threadId;

    @ApiModelProperty(value = "资源类型：image/media/question/vote....")
    private String tomType;

    @ApiModelProperty(value = "元数据索引 $0,$1,$2 资源占位符")
    private String key;

    @ApiModelProperty(value = "资源结构化数据")
    private String value;

    @ApiModelProperty(value = "-1：删除 0：正常")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;


}
