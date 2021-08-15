package cn.chenyuxian.discuz.system.modular.mobilecode.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
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
public class MobileCodes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 验证码 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证类型
     */
    private String type;

    /**
     * 验证状态
     */
    private Integer state;

    /**
     * ip
     */
    private String ip;

    /**
     * 验证码过期时间
     */
    private LocalDateTime expiredAt;


}
