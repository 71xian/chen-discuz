package cn.chenyuxian.discuz.system.modular.invite.entity;

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
public class Invites extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 邀请 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 默认用户组 id
     */
    private Long groupId;

    /**
     * 类型:1普通用户2管理员
     */
    private Integer type;

    /**
     * 邀请码
     */
    private String code;

    /**
     * 邀请码生效时间
     */
    private Integer dateline;

    /**
     * 邀请码结束时间
     */
    private Integer endtime;

    /**
     * 邀请用户 id
     */
    private Long userId;

    /**
     * 被邀请用户 id
     */
    private Long toUserId;

    /**
     * 邀请码状态:0失效1未使用2已使用3已过期
     */
    private Integer status;


}
