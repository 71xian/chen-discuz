package com.aoexe.discuz.system.modular.user.model.entity;

import com.aoexe.discuz.core.context.login.LoginUser;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.List;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable, LoginUser{

    private static final long serialVersionUID = 1L;

    /**
     * 用户 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 签名
     */
    private String signature;

    /**
     * 最后登录 ip 地址
     */
    private String lastLoginIp;

    /**
     * 最后登录端口
     */
    private Integer lastLoginPort;

    /**
     * 注册ip
     */
    private String registerIp;

    /**
     * 注册端口
     */
    private Integer registerPort;

    /**
     * 注册原因
     */
    private String registerReason;

    /**
     * 审核拒绝原因
     */
    private String rejectReason;

    /**
     * 用户名修改次数
     */
    private Integer usernameBout;

    /**
     * 主题数
     */
    private Integer threadCount;

    /**
     * 关注数
     */
    private Integer followCount;

    /**
     * 粉丝数
     */
    private Integer fansCount;

    /**
     * 点赞数
     */
    private Integer likedCount;

    /**
     * 提问数
     */
    private Integer questionCount;

    /**
     * 用户状态：0正常 1禁用 2审核中 3审核拒绝 4审核忽略
     */
    private Integer status;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 背景图地址
     */
    private String background;

    /**
     * 身份证号码
     */
    private String identity;

    /**
     * 身份证姓名
     */
    private String realname;

    /**
     * 头像修改时间
     */
    private LocalDateTime avatarAt;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginAt;

    /**
     * 付费加入时间
     */
    private LocalDateTime joinedAt;

    /**
     * 付费到期时间
     */
    private LocalDateTime expiredAt;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 登录绑定类型；0：默认或微信；2：qq登录；
     */
    private Integer bindType;
    
    @TableField(exist = false)
    private List<String> permissions;


}
