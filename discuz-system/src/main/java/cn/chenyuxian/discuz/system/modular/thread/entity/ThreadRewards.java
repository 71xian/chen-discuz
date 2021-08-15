package cn.chenyuxian.discuz.system.modular.thread.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
import java.math.BigDecimal;
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
public class ThreadRewards extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 悬赏帖ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联的threads主键ID
     */
    private Long threadId;

    /**
     * 关联的posts主键ID
     */
    private Long postId;

    /**
     * 0为所有人回答，1为指定人回答
     */
    private Integer type;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 被指定人ID，可为空
     */
    private Long answerId;

    /**
     * 悬赏金额
     */
    private BigDecimal money;

    /**
     * 剩余的悬赏金额
     */
    private BigDecimal remainMoney;

    /**
     * 过期时间
     */
    private LocalDateTime expiredAt;


}
