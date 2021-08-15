package cn.chenyuxian.discuz.system.modular.group.entity;

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
public class Groups extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户组名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 颜色
     */
    private String color;

    /**
     * icon
     */
    private String icon;

    /**
     * 是否默认
     */
    private Integer isDefault;

    /**
     * 是否显示在用户名后
     */
    private Integer isDisplay;

    /**
     * 是否收费：0不收费，1收费
     */
    private Integer isPaid;

    /**
     * 收费金额
     */
    private BigDecimal fee;

    /**
     * 付费获得天数
     */
    private Long days;

    /**
     * 分成比例
     */
    private Double scale;

    /**
     * 是否可以 推广下线
     */
    private Integer isSubordinate;

    /**
     * 是否可以 收入提成
     */
    private Integer isCommission;


}
