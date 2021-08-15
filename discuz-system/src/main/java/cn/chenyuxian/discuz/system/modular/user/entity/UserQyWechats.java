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
public class UserQyWechats extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户 id
     */
    private Integer userId;

    /**
     * 企业微信企业用户id, corpid_userid联合全局唯一
     */
    private String qyUserid;

    /**
     * 企业微信昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 地址
     */
    private String address;

    /**
     * 头像
     */
    private String headimgurl;


}
