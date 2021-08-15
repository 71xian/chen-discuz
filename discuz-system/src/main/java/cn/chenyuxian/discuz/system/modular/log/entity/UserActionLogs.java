package cn.chenyuxian.discuz.system.modular.log.entity;

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
public class UserActionLogs extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 日志 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作用户 id
     */
    private Long userId;

    /**
     * 操作
     */
    private String action;

    /**
     * 备注
     */
    private String message;

    /**
     * 模型 id
     */
    private Integer logAbleId;

    /**
     * 模型
     */
    private String logAbleType;


}
