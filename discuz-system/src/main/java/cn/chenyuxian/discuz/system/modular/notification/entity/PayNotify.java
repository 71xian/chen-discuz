package cn.chenyuxian.discuz.system.modular.notification.entity;

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
public class PayNotify extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 支付通知 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 支付编号
     */
    private String paymentSn;

    /**
     * 付款人 id
     */
    private Long userId;

    /**
     * 商户平台交易号
     */
    private String tradeNo;

    /**
     * 0未接受到通知，1收到通知
     */
    private Integer status;


}
