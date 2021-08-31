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
@ApiModel(value="UserSignInFields对象", description="")
public class UserSignInFields implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户user_id")
    private Long userId;

    @ApiModelProperty(value = "用户端显示的字段名称")
    private String name;

    @ApiModelProperty(value = "0:单行文本框 1:多行文本框 2:单选 3:复选 4:图片上传 5:附件上传")
    private Integer type;

    @ApiModelProperty(value = "字段扩展信息，Json表示选项内容")
    private String fieldsExt;

    @ApiModelProperty(value = "字段介绍")
    private String fieldsDesc;

    @ApiModelProperty(value = "审核意见")
    private String remark;

    @ApiModelProperty(value = "自定义显示顺序")
    private Integer sort;

    @ApiModelProperty(value = "0:废弃 1:待审核 2:驳回 3:审核通过")
    private Integer status;

    @ApiModelProperty(value = "是否必填项 0:否 1:是")
    private Integer required;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;


}
