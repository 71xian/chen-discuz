package com.aoexe.discuz.system.modular.notification.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Notification implements Serializable {

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

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
