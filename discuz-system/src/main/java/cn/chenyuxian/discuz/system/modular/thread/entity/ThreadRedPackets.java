package cn.chenyuxian.discuz.system.modular.thread.entity;

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
public class ThreadRedPackets extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 红包ID
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
     * 发放规则，0定额，1随机
     */
    private Integer rule;

    /**
     * 领取红包条件，0回复，1集赞
     */
    private Integer condition;

    /**
     * 若红包领取条件为集赞，必填集赞数
     */
    private Integer likenum;

    /**
     * 红包总金额
     */
    private BigDecimal money;

    /**
     * 红包个数
     */
    private Integer number;

    /**
     * 剩余红包总额
     */
    private BigDecimal remainMoney;

    /**
     * 剩余红包个数
     */
    private Integer remainNumber;

    /**
     * 0:红包已过期,1:红包未过期
     */
    private Integer status;


}
