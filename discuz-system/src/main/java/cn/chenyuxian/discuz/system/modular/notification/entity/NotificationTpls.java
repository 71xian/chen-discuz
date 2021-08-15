package cn.chenyuxian.discuz.system.modular.notification.entity;

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
public class NotificationTpls extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模板唯一标识ID
     */
    private String noticeId;

    /**
     * 模板状态:1开启0关闭
     */
    private Integer status;

    /**
     * 通知类型:0系统1微信2短信
     */
    private Integer type;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 可选的变量
     */
    private String vars;

    /**
     * 模板ID
     */
    private String templateId;

    /**
     * first.DATA
     */
    private String firstData;

    /**
     * keywords.DATA
     */
    private String keywordsData;

    /**
     * remark.DATA
     */
    private String remarkData;

    /**
     * data color
     */
    private String color;

    /**
     * 跳转类型：0无跳转 1跳转H5 2跳转小程序
     */
    private Integer redirectType;

    /**
     * 跳转地址
     */
    private String redirectUrl;

    /**
     * 跳转路由
     */
    private String pagePath;

    /**
     * 模板是否配置错误
     */
    private Integer isError;

    /**
     * 错误信息
     */
    private String errorMsg;


}
