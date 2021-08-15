package cn.chenyuxian.discuz.system.modular.notification.entity;

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
public class Notifications extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 通知 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 通知类型
     */
    private String type;

    private String notifiableType;

    private Long notifiableId;

    /**
     * 通知内容
     */
    private String data;

    /**
     * 通知阅读时间
     */
    private LocalDateTime readAt;


}
