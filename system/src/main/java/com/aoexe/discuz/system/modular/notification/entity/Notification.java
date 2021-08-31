package com.aoexe.discuz.system.modular.notification.entity;

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
@ApiModel(value="Notification对象", description="")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通知 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "通知类型")
    private String type;

    private String notifiableType;

    private Long notifiableId;

    @ApiModelProperty(value = "通知内容")
    private String data;

    @ApiModelProperty(value = "通知阅读时间")
    private LocalDateTime readAt;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;


}
