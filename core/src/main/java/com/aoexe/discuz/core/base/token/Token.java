package com.aoexe.discuz.core.base.token;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Token实体类
 *
 * @author chenyuxian
 * @date 2021-08-23
 */
@Data
public class Token implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	private Integer tokenId;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 客户端id
	 */
	private String clientId;
	
	/**
	 * access_token
	 */
	private String accessToken;
	
	/**
	 * refresh_token
	 */
	private String refreshToken;
	
	/**
	 * 签发时间
	 */
	private Date issuedAt;
	
	/**
	 * access_token过期时间
	 */
	private Date accessTokenExpireAt;
	
	/**
	 * refresh_token过期时间
	 */
	private Date refreshTokenExpireAt;
	
	/**
	 * 用户角色
	 */
	private String role;
	
}