package cn.chenyuxian.discuz.system.modular.user.entity;

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
public class UserWalletCash extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 提现 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 提现用户 id
     */
    private Long userId;

    /**
     * 提现交易编号
     */
    private Long cashSn;

    /**
     * 提现手续费
     */
    private BigDecimal cashCharge;

    /**
     * 实际提现金额
     */
    private BigDecimal cashActualAmount;

    /**
     * 提现申请金额
     */
    private BigDecimal cashApplyAmount;

    /**
     * 提现状态：1：待审核，2：审核通过，3：审核不通过，4：待打款， 5，已打款， 6：打款失败
     */
    private Integer cashStatus;

    /**
     * 提现到账手机号码
     */
    private String cashMobile;

    /**
     * 提现转账类型：0：人工转账， 1：企业零钱付款
     */
    private Integer cashType;

    /**
     * 备注或原因
     */
    private String remark;

    /**
     * 交易时间
     */
    private LocalDateTime tradeTime;

    /**
     * 交易号
     */
    private String tradeNo;

    /**
     * 错误代码
     */
    private String errorCode;

    /**
     * 交易失败描叙
     */
    private String errorMessage;

    /**
     * 返款状态，0未返款，1已返款
     */
    private Integer refundsStatus;


}
