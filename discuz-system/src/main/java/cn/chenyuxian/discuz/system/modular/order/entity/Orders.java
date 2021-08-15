package cn.chenyuxian.discuz.system.modular.order.entity;

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
public class Orders extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 支付编号
     */
    private String paymentSn;

    /**
     * 订单总金额
     */
    private BigDecimal amount;

    /**
     * 站长分成金额
     */
    private BigDecimal masterAmount;

    /**
     * 作者分成金额
     */
    private BigDecimal authorAmount;

    /**
     * 第三者收益金额
     */
    private BigDecimal thirdPartyAmount;

    /**
     * 作者受邀时的分成比例
     */
    private Double beScale;

    /**
     * 付款人 id
     */
    private Long userId;

    /**
     * 收款人 id
     */
    private Long payeeId;

    /**
     * 第三者收益人 id
     */
    private Long thirdPartyId;

    /**
     * 交易类型：1注册、2打赏、3付费主题、4付费用户组
     */
    private Integer type;

    /**
     * 用户组 id
     */
    private Long groupId;

    /**
     * 主题 id
     */
    private Long threadId;

    /**
     * 订单状态：0待付款；1已付款；2.取消订单；3支付失败；4订单过期
     */
    private Integer status;

    /**
     * 付款方式：微信（10：pc扫码，11：h5支付，12：微信内支付
     */
    private Integer paymentType;

    /**
     * 是否匿名(0否1是)
     */
    private Integer isAnonymous;

    /**
     * 关联的posts主键ID
     */
    private Long postId;

    /**
     * 退款金额
     */
    private BigDecimal refund;

    /**
     * 付费注册过期时长
     */
    private LocalDateTime expiredAt;

    /**
     * 退款时间
     */
    private LocalDateTime returnAt;


}
