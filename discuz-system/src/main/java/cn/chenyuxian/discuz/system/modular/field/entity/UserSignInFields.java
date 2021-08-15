package cn.chenyuxian.discuz.system.modular.field.entity;

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
public class UserSignInFields extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户user_id
     */
    private Long userId;

    /**
     * 用户端显示的字段名称
     */
    private String name;

    /**
     * 0:单行文本框 1:多行文本框 2:单选 3:复选 4:图片上传 5:附件上传
     */
    private Integer type;

    /**
     * 字段扩展信息，Json表示选项内容
     */
    private String fieldsExt;

    /**
     * 字段介绍
     */
    private String fieldsDesc;

    /**
     * 审核意见
     */
    private String remark;

    /**
     * 自定义显示顺序
     */
    private Integer sort;

    /**
     * 0:废弃 1:待审核 2:驳回 3:审核通过
     */
    private Integer status;

    /**
     * 是否必填项 0:否 1:是
     */
    private Integer required;


}
