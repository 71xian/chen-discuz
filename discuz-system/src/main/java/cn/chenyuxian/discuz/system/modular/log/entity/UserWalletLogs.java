package cn.chenyuxian.discuz.system.modular.log.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
import java.math.BigDecimal;
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
public class UserWalletLogs extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 钱包明细 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 明细所属用户 id
     */
    private Long userId;

    /**
     * 金额来源用户
     */
    private Long sourceUserId;

    /**
     * 变动可用金额
     */
    private BigDecimal changeAvailableAmount;

    /**
     * 变动冻结金额
     */
    private BigDecimal changeFreezeAmount;

    /**
     * 10：提现冻结，11：提现成功，12：撤销提现解冻； 31：打赏收入，32：人工收入； 50：人工支出
     */
    private Integer changeType;

    /**
     * 变动描述
     */
    private String changeDesc;

    /**
     * 关联订单记录ID
     */
    private Long orderId;

    /**
     * 关联提现记录ID
     */
    private Long userWalletCashId;

    /**
     * 关联问答记录 id
     */
    private Long questionId;

    /**
     * 关联的threads主键ID
     */
    private Long threadId;

    /**
     * 关联的posts主键ID
     */
    private Long postId;


}
