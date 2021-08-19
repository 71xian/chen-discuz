package cn.chenyuxian.discuz.core.common.log;

import lombok.Getter;

/**
 * 日志类型枚举
 *
 * @author chenyuxian
 * @date 2021-08-10
 */
@Getter
public enum DiscuzQLogType {

	 /**
	  * 容器全局变量
	  */
	 APP_DZQLOG("APP_DZQLOG"),
	 
	 /**
	  * 微信日志
	  */
     LOG_WECHAT("wechatLog"),
     
     /**
      * 支付日志
      */
     LOG_PAY("payLog"),
     
     /**
      * 腾讯云日志
      */
     LOG_QCLOUND("qcloudLog"),
     
     /**
      * 微信官方账号日志
      */
     LOG_WECHAT_OFFIACCOUNT("wechatOffiaccount"),
     
     /**
      * 平台日志
      */
     LOG_PERFORMANCE("performancelog"),
     
     /**
      * 登录日志
      */
     LOG_LOGIN("loginLog"),
     
     /**
      * 管理员日志
      */
     LOG_ADMIN("adminLog"),
     
     /**
      * 接口日志
      */
     LOG_API("apiLog"),
     
     /**
      * 错误日志
      */
     LOG_ERROR("errorLog"),
     
     /**
      * info级别日志
      */
     LOG_INFO("log");
	
	private final String code;

	private DiscuzQLogType(String code) {
		this.code = code;
	}
	
	
}
