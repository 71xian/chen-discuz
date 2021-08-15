package cn.chenyuxian.discuz.system.modular.finance.entity;

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
public class Finance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户充值金额
     */
    private BigDecimal income;

    /**
     * 用户提现金额
     */
    private BigDecimal withdrawal;

    /**
     * 订单数量
     */
    private Integer orderCount;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 平台盈利
     */
    private BigDecimal totalProfit;

    /**
     * 注册收入
     */
    private BigDecimal registerProfit;

    /**
     * 打赏贴的分成
     */
    private BigDecimal masterPortion;

    /**
     * 提现手续费收入
     */
    private BigDecimal withdrawalProfit;


}
