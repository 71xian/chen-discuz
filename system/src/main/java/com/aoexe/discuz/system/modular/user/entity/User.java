package com.aoexe.discuz.system.modular.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.aoexe.discuz.core.context.login.LoginUser;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "User对象", description = "")
public class User implements Serializable, LoginUser{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户 id")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "密码")
	@JsonIgnore
	private String password;

	@ApiModelProperty(value = "用户昵称")
	private String nickname;

	@ApiModelProperty(value = "支付密码")
	@JsonIgnore
	private String payPassword;

	@ApiModelProperty(value = "手机号")
	private String mobile;

	@ApiModelProperty(value = "签名")
	private String signature;

	@ApiModelProperty(value = "最后登录 ip 地址")
	private String lastLoginIp;

	@ApiModelProperty(value = "最后登录端口")
	private Integer lastLoginPort;

	@ApiModelProperty(value = "注册ip")
	private String registerIp;

	@ApiModelProperty(value = "注册端口")
	private Integer registerPort;

	@ApiModelProperty(value = "注册原因")
	private String registerReason;

	@ApiModelProperty(value = "审核拒绝原因")
	private String rejectReason;

	@ApiModelProperty(value = "用户名修改次数")
	private Integer usernameBout;

	@ApiModelProperty(value = "主题数")
	private Integer threadCount;

	@ApiModelProperty(value = "关注数")
	private Integer followCount;

	@ApiModelProperty(value = "粉丝数")
	private Integer fansCount;

	@ApiModelProperty(value = "点赞数")
	private Integer likedCount;

	@ApiModelProperty(value = "提问数")
	private Integer questionCount;

	@ApiModelProperty(value = "用户状态：0正常 1禁用 2审核中 3审核拒绝 4审核忽略")
	private Integer status;

	@ApiModelProperty(value = "头像地址")
	private String avatar;

	@ApiModelProperty(value = "背景图地址")
	private String background;

	@ApiModelProperty(value = "身份证号码")
	private String identity;

	@ApiModelProperty(value = "身份证姓名")
	private String realname;

	@ApiModelProperty(value = "头像修改时间")
	private Date avatarAt;

	@ApiModelProperty(value = "最后登录时间")
	private Date loginAt;

	@ApiModelProperty(value = "付费加入时间")
	private Date joinedAt;

	@ApiModelProperty(value = "付费到期时间")
	private Date expiredAt;

	@ApiModelProperty(value = "创建时间")
	private Date createdAt;

	@ApiModelProperty(value = "更新时间")
	private Date updatedAt;

	@ApiModelProperty(value = "登录绑定类型；0：默认或微信；2：qq登录；")
	private Integer bindType;

	@TableField(exist = false)
	private Set<String> permissions;

}
