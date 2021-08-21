package cn.chenyuxian.discuz.core.enums;

import lombok.Getter;

/**
 * 响应码枚举类
 *
 * @author chenyuxian
 * @date 2021-08-14
 */
@Getter
public enum DiscuzQCode {

	/**
	 * 接口调用成功
	 */
	SUCCESS(0, "接口调用成功"),

	/**
	 * 当前站点未安装
	 */
	NOT_INSTALL(-10001, "当前站点未安装"),

	/**
	 * 无效未知URL地址
	 */
	WECHAT_INVALID_UNKNOWN_URL_EXCEPTION(-2001, "无效未知URL地址"),

	/**
	 * 无效配置
	 */
	WECHAT_INVALID_CONFIG_EXCEPTION(-2002, "无效配置"),

	/**
	 * 运行时异常
	 */
	WECHAT_RUNTIME_EXCEPTION(-2003, "运行时异常"),

	/**
	 * 无效参数
	 */
	WECHAT_INVALID_ARGUMENT_EXCEPTION(-2004, "无效参数"),

	/**
	 * 跳转到登录页
	 */
	JUMP_TO_LOGIN(-3001, "跳转到登录页"),

	/**
	 * 跳转到注册页
	 */
	JUMP_TO_REGISTER(-3002, "跳转到注册页"),

	/**
	 * 跳转到审核页
	 */
	JUMP_TO_AUDIT(-3003, "跳转到审核页"),

	/**
	 * 跳转到首页
	 */
	JUMP_TO_HOME_INDEX(-3004, "跳转到首页"),

	/**
	 * 站点已关闭
	 */
	SITE_CLOSED(-3005, "站点已关闭"),

	/**
	 * 跳转到站点付费页
	 */
	JUMP_TO_PAY_SITE(-3006, "跳转到站点付费页"),

	/**
	 * 跳转到扩展字段页
	 */
	JUMP_TO_SIGIN_FIELDS(-3007, "跳转到扩展字段页"),

	/**
	 * 参数错误
	 */
	INVALID_PARAMETER(-4001, "参数错误"),

	/**
	 * 没有权限
	 */
	UNAUTHORIZED(-4002, "没有权限"),

	/**
	 * 资源已存在
	 */
	RESOURCE_EXIST(-4003, "资源已存在"),

	/**
	 * 资源不存在
	 */
	RESOURCE_NOT_FOUND(-4004, "资源不存在"),

	/**
	 * 资源被占用
	 */
	RESOURCE_IN_USE(-4005, "资源被占用"),

	/**
	 * 内容被禁用
	 */
	CONTENT_BANNED(-4006, "内容被禁用"),

	/**
	 * 审核不通过
	 */
	VALIDATE_REJECT(-4007, "审核不通过"),

	/**
	 * 忽略审核
	 */
	VALIDATE_IGNORE(-4008, "忽略审核"),

	/**
	 * 禁用用户
	 */
	USER_BAN(-4009, "禁用用户"),

	/**
	 * 资源已过期
	 */
	RESOURCE_EXPIRED(-4010, "资源已过期"),

	/**
	 * 无效token
	 */
	INVALID_TOKEN(-4011, "无效token"),

	/**
	 * 网络错误
	 */
	NET_ERROR(-5001, "网络错误"),

	/**
	 * 内部系统错误
	 */
	INTERNAL_ERROR(-5002, "内部系统错误"),

	/**
	 * 数据库错误
	 */
	DB_ERROR(-5003, "数据库错误"),

	/**
	 * 外部接口错误
	 */
	EXTERNAL_API_ERROR(-5004, "外部接口错误"),

	/**
	 * 敏感词校验未通过
	 */
	CENSOR_NOT_PASSED(-5005, "敏感词校验未通过"),

	/**
	 * 未知错误
	 */
	UNKNOWN_ERROR(-6001, "未知错误"),

	/**
	 * 调试错误
	 */
	DEBUG_ERROR(-6002, "调试错误"),

	/**
	 * 二维码已失效，扫码超时
	 */
	PC_QRCODE_TIME_OUT(-7001, "二维码已失效，扫码超时"),

	/**
	 * 扫码中
	 */
	PC_QRCODE_SCANNING_CODE(-7002, "扫码中"),

	/**
	 * 扫码失败，请重新扫码
	 */
	PC_QRCODE_ERROR(-7003, "扫码失败，请重新扫码"),

	/**
	 * SESSION TOKEN过期
	 */
	SESSION_TOKEN_EXPIRED(-7004, "SESSION TOKEN过期"),

	/**
	 * 未找到用户
	 */
	NOT_FOUND_USER(-7005, "未找到用户"),

	/**
	 * 未找到微信用户
	 */
	NOT_FOUND_USER_WECHAT(-7006, "未找到微信用户"),

	/**
	 * 扫码登录失败
	 */
	PC_QRCODE_TIME_FAIL(-7007, "扫码登录失败 "),

	/**
	 * 生成二维码参数类型错误
	 */
	GEN_QRCODE_TYPE_ERROR(-7008, "生成二维码参数类型错误"),

	/**
	 * 全局token获取失败
	 */
	MINI_PROGRAM_GET_ACCESS_TOKEN_ERROR(-7009, "全局token获取失败"),

	/**
	 * 小程序二维码生成失败
	 */
	MINI_PROGRAM_QR_CODE_ERROR(-7010, "小程序二维码生成失败"),

	/**
	 * 绑定失败
	 */
	PC_BIND_ERROR(-7011, "绑定失败"),

	/**
	 * 生成scheme失败
	 */
	MINI_PROGRAM_SCHEME_ERROR(-7012, "生成scheme失败"),

	/**
	 * 解密邀请码失败
	 */
	DECRYPT_CODE_FAILURE(-7013, "解密邀请码失败"),

	/**
	 * 需要绑定或注册用户
	 */
	NEED_BIND_USER_OR_CREATE_USER(-7016, "需要绑定或注册用户"),

	/**
	 * 解密邀请码失败
	 */
	REGISTER_DECRYPT_CODE_FAILED(-7014, "解密邀请码失败"),

	/**
	 * 未用到
	 */
	NOT_AUTHENTICATED(-7015, "未用到"), // 未用到

	/**
	 * 换绑失败
	 */
	PC_REBIND_ERROR(-7017, "换绑失败"),

	/**
	 * 手机号已被绑定
	 */
	MOBILE_IS_ALREADY_BIND(-7031, "手机号已被绑定"),

	/**
	 * 站点关闭注册
	 */
	REGISTER_CLOSE(-7032, "站点关闭注册"),

	/**
	 * 注册类型错误
	 */
	REGISTER_TYPE_ERROR(-7033, "注册类型错误"),

	/**
	 * 不可以使用相同的密码
	 */
	USER_UPDATE_ERROR(-7034, "不可以使用相同的密码"),

	/**
	 * 请验证旧的手机号
	 */
	VERIFY_OLD_PHONE_NUMBER(-7035, "请验证旧的手机号"),

	/**
	 * 请输入新的手机号
	 */
	ENTER_NEW_PHONE_NUMBER(-7036, "请输入新的手机号"),

	/**
	 * 账号已被绑定
	 */
	ACCOUNT_HAS_BEEN_BOUND(-7037, "账号已被绑定"),

	/**
	 * 账号微信为空
	 */
	ACCOUNT_WECHAT_IS_NULL(-7038, "账号微信为空"),

	/**
	 * 绑定错误
	 */
	BIND_ERROR(-7039, "绑定错误"),

	/**
	 * 登陆失败
	 */
	LOGIN_FAILED(-7040, "登陆失败"),

	/**
	 * 用户名或昵称长度超过15个字符
	 */
	NAME_LENGTH_ERROR(-7041, "用户名或昵称长度超过15个字符"),

	/**
	 * 用户名已经存在
	 */
	USERNAME_HAD_EXIST(-7042, "用户名已经存在"),

	/**
	 * 短信服务未开启
	 */
	SMS_SERVICE_ENABLED(-7043, "短信服务未开启"),

	/**
	 * 需要绑定微信
	 */
	BIND_TYPE_IS_NULL(-7044, "需要绑定微信"),

	/**
	 * 授权信息已过期，请重新授权
	 */
	AUTH_INFO_HAD_EXPIRED(-7045, "授权信息已过期，请重新授权"),

	/**
	 * 用户绑定类型不存在
	 */
	USER_BIND_TYPE_IS_NULL(-7046, "用户绑定类型不存在"),

	/**
	 * 参数不为对象
	 */
	PARAM_IS_NOT_OBJECT(-7047, "参数不为对象"),

	/**
	 * 过渡开关未开启
	 */
	TRANSITION_NOT_OPEN(-7048, "过渡开关未开启"),

	/**
	 * 用户名不能为空
	 */
	USERNAME_NOT_NULL(-7049, "用户名不能为空"),

	/**
	 * 用户登录态不能为空
	 */
	USER_LOGIN_STATUS_NOT_NULL(-7050, "用户登录态不能为空"),

	/**
	 * 该网站暂不支持微信换绑功能
	 */
	NONSUPPORT_WECHAT_REBIND(-7051, "该网站暂不支持微信换绑功能"),

	/**
	 * 用户id不允许为空
	 */
	USERID_NOT_ALLOW_NULL(-7052, "用户id不允许为空"),

	/**
	 * 用户手机号不允许为空
	 */
	USER_MOBILE_NOT_ALLOW_NULL(-7053, "用户手机号不允许为空"),

	/**
	 * 真实姓名不能为空
	 */
	REALNAME_NOT_NULL(-7054, "真实姓名不能为空"),

	/**
	 * 身份证不能为空
	 */
	IDENTITY_NOT_NULL(-7055, "身份证不能为空"),

	/**
	 * 实名认证不通过
	 */
	REAL_USER_CHECK_FAIL(-7056, "实名认证不通过"),

	/**
	 * 昵称未通过铭感词校验
	 */
	NICKNAME_CENSOR_NOT_PASSED(-7057, "昵称未通过铭感词校验"),

	/**
	 * 用户签名限制错误
	 */
	USER_SINGATURE_LINIT_ERROR(-7058, "用户签名限制错误"),

	/**
	 * 不能关注自己
	 */
	NOT_FOLLOW_YOURSELE(-7059, "不能关注自己"),

	/**
	 * 关注用户不存在
	 */
	NOT_FOLLOW_USER(-7060, "关注用户不存在"),

	/**
	 * 根据对方的设置，您无法进行该操作
	 */
	HAS_BEEN_BLOCKED_BY_THE_OPPOSITION(-7061, "根据对方的设置，您无法进行该操作"),

	/**
	 * 用户名或密码错误
	 */
	USERNAME_OR_PASSWORD_ERROR(-7062, "用户名或密码错误"),

	/**
	 * 不能换绑自己的手机号
	 */
	NOT_REBIND_SELF_MOBILE(-7063, "不能换绑自己的手机号"),

	/**
	 * 该网站暂不支持手机绑定功能
	 */
	NONSUPPORT_MOBILE_BIND(-7064, "该网站暂不支持手机绑定功能"),

	/**
	 * 该网站暂不支持手机换绑功能
	 */
	NONSUPPORT_MOBILE_REBIND(-7065, "该网站暂不支持手机换绑功能"),

	/**
	 * 原有手机号验证码处理失败
	 */
	ORIGINAL_USER_MOBILE_VERIFY_ERROR(-7066, "原有手机号验证码处理失败"),

	/**
	 * 密码输入非法
	 */
	PASSWORD_ILLEGALITY(-7067, "密码输入非法"),

	/**
	 * 根据你的设置，您无法进行该操作
	 */
	YOU_BLOCKED_HIM(-7068, "根据你的设置，您无法进行该操作"),

	/**
	 * 密码输入非法
	 */
	PASSWORD_NOT_ALLOW_HAS_SPACE(-7069, "密码输入非法"),

	/**
	 * 用户需填写扩展字段
	 */
	USER_NEED_SIGNIN_FIELDS(-7070, "用户需填写扩展字段"),

	/**
	 * 用户审核中
	 */
	USER_IN_REVIEW(-7071, "用户审核中"),

	/**
	 * 请付费加入站点
	 */
	PAY_JOIN_SITE(-7072, "请付费加入站点"),

	/**
	 * 用户名不允许包含空格
	 */
	USERNAME_NOT_ALLOW_HAS_SPACE(-7073, "用户名不允许包含空格"),

	/**
	 * 当前注册人数过多，请稍后登录
	 */
	TRY_LOGIN_AGAIN(-7074, "当前注册人数过多，请稍后登录"),

	/**
	 * 不允许上传敏感图
	 */
	NOT_ALLOW_CENSOR_IMAGE(-7075, "不允许上传敏感图"),

	/**
	 * 分类不存在
	 */
	CATEGORY_NOT_FOUNF(-7076, "分类不存在"),

	/**
	 * 需要绑定微信
	 */
	NEED_BIND_WECHAT(-8000, "需要绑定微信"),

	/**
	 * 需要绑定手机
	 */
	NEED_BIND_PHONE(-8001, "需要绑定手机"),

	/**
	 * 短信未开启
	 */
	SMS_NOT_OPEN(-9001, "短信未开启"),

	/**
	 * 验证码错误
	 */
	SMS_CODE_ERROR(-9002, "验证码错误"),

	/**
	 * 验证码已过期
	 */
	SMS_CODE_EXPIRE(-9003, "验证码已过期"),

	/**
	 * 支付失败
	 */
	PAY_ORDER_FAIL(-10000, "支付失败");

	private final Integer code;

	private final String message;

	private DiscuzQCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

}
