package cn.chenyuxian.discuz.system.modular.dialog.entity;

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
public class DialogMessage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会话ID
     */
    private Long dialogId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 附件ID
     */
    private Long attachmentId;

    /**
     * 内容
     */
    private String messageText;

    /**
     * 阅读状态0.未读 1.已读
     */
    private Integer readStatus;

    /**
     * 消息状态 0空消息 1.正常消息
     */
    private Integer status;


}
