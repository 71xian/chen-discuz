package com.aoexe.discuz.system.modular.notification.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="NotificationTpls对象", description="")
public class NotificationTpls implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "模板唯一标识ID")
    private String noticeId;

    @ApiModelProperty(value = "模板状态:1开启0关闭")
    private Integer status;

    @ApiModelProperty(value = "通知类型:0系统1微信2短信")
    private Integer type;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "可选的变量")
    private String vars;

    @ApiModelProperty(value = "模板ID")
    private String templateId;

    @ApiModelProperty(value = "first.DATA")
    private String firstData;

    @ApiModelProperty(value = "keywords.DATA")
    private String keywordsData;

    @ApiModelProperty(value = "remark.DATA")
    private String remarkData;

    @ApiModelProperty(value = "data color")
    private String color;

    @ApiModelProperty(value = "跳转类型：0无跳转 1跳转H5 2跳转小程序")
    private Integer redirectType;

    @ApiModelProperty(value = "跳转地址")
    private String redirectUrl;

    @ApiModelProperty(value = "跳转路由")
    private String pagePath;

    @ApiModelProperty(value = "模板是否配置错误")
    private Integer isError;

    @ApiModelProperty(value = "错误信息")
    private String errorMsg;


}
