package com.aoexe.discuz.core.util;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.aoexe.discuz.core.base.token.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Token工具类
 *
 * @author chenyuxian
 * @date 2021-08-23
 */
public final class TokenUtil implements Serializable{

	private static final long serialVersionUID = -1009940167542615519L;

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
	
	private static Integer refreshTokenExpireTime = 7;
	
	/**
	 * 生成token
	 *
	 * @param username
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-23
	 */
	public static Token generateToken(String username) {
		Instant now = Instant.now();
		// accessToken过期时间为2小时
		Date accessTokenExpireDate = Date.from(now.plus(accessTokenExpireTime, ChronoUnit.HOURS));
		// refreshToken过期时间为7天
		Date refreshTokenExpireDate = Date.from(now.plus(refreshTokenExpireTime * 7, ChronoUnit.DAYS));
		
		Token token = new Token();
		token.setUsername(username);
		token.setIssuedAt(Date.from(now));
		
		String accessToken =  Jwts.builder()
				.setSubject(username)
				.setExpiration(accessTokenExpireDate)
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		token.setAccessToken(accessToken);
		token.setAccessTokenExpireAt(accessTokenExpireDate);
		
		String refreshToken = Jwts.builder()
				.setSubject(username)
				.setExpiration(refreshTokenExpireDate)
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		token.setRefreshToken(refreshToken);
		token.setRefreshTokenExpireAt(refreshTokenExpireDate);
		return token;
	}
	
	/**
	 * 解析token
	 *
	 * @param token
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-23
	 */
	public static Claims parseToken(String token) {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}
	
	public static boolean checkToken(String token) {
		if(token == null) return false;
		try {
			Claims claims = parseToken(token);
			return claims != null;
		} catch (JwtException e) {
			System.out.println("token验证失败");
		}
		return false;
	}
	
	/**
	 * 验证token是否过期
	 *
	 * @param token
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-23
	 */
	public static boolean exipreToken(String token) {
		return parseToken(token).getExpiration().before(new Date());
	}
	
	/**
	 * 从请求头中获取token
	 *
	 * @param request
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-23
	 */
	public static String getToken(HttpServletRequest request) {
		String token = request.getHeader(HEADER_NAME);
		if(token != null && token.startsWith(TOKEN_TYPE)) {
			return token.substring(TOKEN_TYPE.length());
		}
		return null;
	}
	
}
