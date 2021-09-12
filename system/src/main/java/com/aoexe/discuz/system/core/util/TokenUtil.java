package com.aoexe.discuz.system.core.util;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.system.modular.config.service.IConfigService;

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
@Component
public final class TokenUtil {

	private String HEADER_NAME = "Authorization";

	private String TOKEN_TYPE = "Bearer ";
	
	@Autowired
	private IConfigService configService;

	public String buildAccessToken(Long userId, String uuid, Date date) {
		return Jwts.builder().setSubject(userId.toString()).setId(uuid)
				.setExpiration(Date.from(date.toInstant().plus(2, ChronoUnit.HOURS)))
				.signWith(SignatureAlgorithm.HS512, configService.getValueByKey("site_secret")).compact();
	}

	public String buildRefreshToken(Long userId, String uuid, Date date, String clientSecret) {
		return Jwts.builder().setSubject(userId.toString()).setId(uuid)
				.setExpiration(Date.from(date.toInstant().plus(7, ChronoUnit.DAYS)))
				.signWith(SignatureAlgorithm.HS512, clientSecret).compact();
	}

	public Claims getClaims(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(configService.getValueByKey("site_secret")).parseClaimsJws(token).getBody();
		} catch (JwtException e) {
			throw new BaseException(ResponseEnum.SESSION_TOKEN_EXPIRED);
		}
		return claims;
	}

	public Claims getRefreshClaims(String refreshToken, String clientSecret) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(clientSecret).parseClaimsJws(refreshToken).getBody();
		} catch (JwtException e) {
			throw new BaseException(ResponseEnum.SESSION_TOKEN_EXPIRED);
		}
		return claims;
	}

	public String getAccessToken(HttpServletRequest request) {
		String token = request.getHeader(HEADER_NAME);
		if (token != null && token.startsWith(TOKEN_TYPE)) {
			return token.substring(TOKEN_TYPE.length());
		}
		return null;
	}

}
