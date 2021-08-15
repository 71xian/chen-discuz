package cn.chenyuxian.discuz.system.modular.dialog.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class Dialog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 最新消息ID
     */
    private Long dialogMessageId;

    /**
     * 发送人UID
     */
    private Long senderUserId;

    /**
     * 收信人UID
     */
    private Long recipientUserId;

    /**
     * 收信人阅读时间
     */
    private LocalDateTime recipientReadAt;

    /**
     * 发送人阅读时间
     */
    private LocalDateTime senderReadAt;

    /**
     * 发送人删除时间
     */
    private LocalDateTime senderDeletedAt;

    /**
     * 收信人删除时间
     */
    private LocalDateTime recipientDeletedAt;


}
