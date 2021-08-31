package com.aoexe.discuz.system.modular.user.entity;

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
@ApiModel(value="Invites对象", description="")
public class Invites implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "邀请 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "默认用户组 id")
    private Long groupId;

    @ApiModelProperty(value = "类型:1普通用户2管理员")
    private Integer type;

    @ApiModelProperty(value = "邀请码")
    private String code;

    @ApiModelProperty(value = "邀请码生效时间")
    private Integer dateline;

    @ApiModelProperty(value = "邀请码结束时间")
    private Integer endtime;

    @ApiModelProperty(value = "邀请用户 id")
    private Long userId;

    @ApiModelProperty(value = "被邀请用户 id")
    private Long toUserId;

    @ApiModelProperty(value = "邀请码状态:0失效1未使用2已使用3已过期")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;


}
