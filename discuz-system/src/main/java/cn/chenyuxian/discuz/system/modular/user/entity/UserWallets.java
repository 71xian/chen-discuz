package cn.chenyuxian.discuz.system.modular.user.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
import java.math.BigDecimal;
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
public class UserWallets extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 钱包所属人 id
     */
    private Long userId;

    /**
     * 可用金额
     */
    private BigDecimal availableAmount;

    /**
     * 冻结金额
     */
    private BigDecimal freezeAmount;

    /**
     * 钱包状态:0正常，1冻结提现
     */
    private Integer walletStatus;


}
