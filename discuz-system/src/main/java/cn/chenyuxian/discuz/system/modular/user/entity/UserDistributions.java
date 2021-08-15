package cn.chenyuxian.discuz.system.modular.user.entity;

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
public class UserDistributions extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级id
     */
    private Long pid;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 受邀时的分成比例
     */
    private Double beScale;

    /**
     * 当前用户所处深度
     */
    private Integer level;

    /**
     * 是否可以 推广下线
     */
    private Integer isSubordinate;

    /**
     * 是否可以 收入提成
     */
    private Integer isCommission;


}
