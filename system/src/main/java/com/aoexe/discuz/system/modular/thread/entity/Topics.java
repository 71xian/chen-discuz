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
@ApiModel(value="Topics对象", description="")
public class Topics implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "话题ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "user_id")
    private Long userId;

    @ApiModelProperty(value = "话题名称")
    private String content;

    @ApiModelProperty(value = "主题数")
    private Integer threadCount;

    @ApiModelProperty(value = "阅读数")
    private Integer viewCount;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "是否推荐")
    private Integer recommended;

    @ApiModelProperty(value = "推荐时间")
    private LocalDateTime recommendedAt;


}
