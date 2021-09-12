package com.aoexe.discuz.system.modular.group.model.entity;

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
 * @since 2021-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GroupPaidUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属用户
     */
    private Long userId;

    /**
     * 用户组 id
     */
    private Long groupId;

    /**
     * 订单 id
     */
    private Long orderId;

    /**
     * 操作人
     */
    private Long operatorId;

    /**
     * 删除类型：1到期删除，2管理员修改，3用户复购
     */
    private Integer deleteType;

    /**
     * 用户组到期时间
     */
    private LocalDateTime expirationTime;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;


}
