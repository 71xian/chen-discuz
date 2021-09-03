package com.aoexe.discuz.core.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.token.Token;

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
public final class TokenUtil {

	private static String SECRET = "chenyuxian.cn";

	private static String HEADER_NAME = "Authorization";

	private static String TOKEN_TYPE = "Bearer ";

	private static Integer accessTokenExpireTime = 2;

	@SuppressWarnings("unchecked")
	public static String generateToken(Token token) {
		Instant now = Instant.now();
		return Jwts.builder()
				.setClaims(JSON.parseObject(JSON.toJSONString(token), Map.class))
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(accessTokenExpireTime, ChronoUnit.HOURS)))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
	}

	public static Claims getClaims(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token)
					.getBody();
		}catch (JwtException e) {
			throw new BaseException(ResponseEnum.SESSION_TOKEN_EXPIRED);
		}
		return claims;
	}

	public static Token getTokenFromStr(String tokenStr) {
		return JSON.parseObject(JSON.toJSONString(getClaims(tokenStr)), Token.class);
	}

	public static String getTokenString(HttpServletRequest request) {
		String token = request.getHeader(HEADER_NAME);
		if (token != null && token.startsWith(TOKEN_TYPE)) {
			return token.substring(TOKEN_TYPE.length());
		}
		return null;
	}

	public static boolean checkToken(String token) {
		return getClaims(token) != null;
	}

	public static boolean isExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}

}
