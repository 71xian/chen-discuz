package com.aoexe.discuz.system.core.token;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Token工具类
 *
 * @author chenyuxian
 * @date 2021-08-23
 */
public final class TokenUtil {

	/**
	 * 密匙
	 */
	private static String SECRET = "chenyuxian.cn";

	/**
	 * Token请求头
	 */
	private static String HEADER_NAME = "Authorization";

	/**
	 * Token前缀
	 */
	private static String TOKEN_TYPE = "Bearer ";

	private static Integer accessTokenExpireTime = 2;

	/**
	 * 生成accessToken
	 *
	 * @param username
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-23
	 */
	public static String generateToken(Token token) {
		Instant now = Instant.now();
		return Jwts.builder()
				.setClaims(JSON.parseObject(JSON.toJSONString(token), Map.class))
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(accessTokenExpireTime, ChronoUnit.DAYS)))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	/**
	 * 获取claims
	 *
	 * @param token
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-23
	 */
	public static Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token)
				.getBody();
	}

	/**
	 * 获取token 
	 *
	 * @param token
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-30
	 */
	public static Token getToken(String token) {
		Claims claims = getClaims(token);
		return JSON.parseObject(JSON.toJSONString(claims), Token.class);
	}
	
	/**
	 * 从请求头中获取token字符串
	 *
	 * @param request
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-23
	 */
	public static String getTokenString(HttpServletRequest request) {
		String token = request.getHeader(HEADER_NAME);
		if (token != null && token.startsWith(TOKEN_TYPE)) {
			return token.substring(TOKEN_TYPE.length());
		}
		throw new BaseException(ResponseEnum.INVALID_TOKEN);
	}
	
	/**
	 * 检验token是否有效
	 *
	 * @param token
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-30
	 */
	public static boolean checkToken(String token) {
		try {
			getClaims(token);
			return true;
		}catch (JwtException e) {
			return false;
		}
	}
	
	/**
	 * 检验token是否过期
	 *
	 * @param token
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-30
	 */
	public static boolean isExpired(String token) {
		try {
			Date expiration = getClaims(token).getExpiration();
			return expiration.before(new Date());
		} catch (ExpiredJwtException e) {
			return true;
		}
	}

}
