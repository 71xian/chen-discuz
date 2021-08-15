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
public class OrderChildren extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 关联的threads主键ID
     */
    private Long threadId;

    /**
     * 订单类型：1注册，2打赏，3购买主题，4购买权限组，5付费提问，6问答围观，7购买附件，8站点付费，9红包，10悬赏，11合并订单
     */
    private Integer type;

    /**
     * 订单状态：0待付款，1已付款，2取消，3支付失败，4过期，5部分退款，10全额退款，11异常订单
     */
    private Integer status;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 退款金额
     */
    private BigDecimal refund;

    /**
     * 退款时间
     */
    private LocalDateTime returnAt;


}
