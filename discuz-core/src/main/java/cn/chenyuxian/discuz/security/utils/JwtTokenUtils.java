package cn.chenyuxian.discuz.security.utils;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.chenyuxian.discuz.security.auth.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils implements Serializable{

	private static final long serialVersionUID = -1009940167542615519L;

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.header}")
	private String header;
	
	@Value("${jwt.prefix}")
	private String prefix;
	
	@Value("${jwt.expired}")
	private Integer expired;
	
	public String generateToken(SecurityUser user) {
		Map<String, Object> claims = new HashMap<>(1);
		claims.put("user", user);
		Instant now = Instant.now();
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(user.getUsername())
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(expired, ChronoUnit.DAYS)))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	public boolean checkToken(String token) {
		try {
			Claims claims = getClaims(token);
			return claims != null;
		} catch (JwtException e) {
			System.out.println("token验证失败");
		}
		return false;
	}
	
	public boolean exipreToken(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}
	
	public String getToken(HttpServletRequest request) {
		String token = request.getHeader(header);
		if(token != null && token.startsWith(prefix)) {
			return token.substring(prefix.length());
		}
		return null;
	}
	
}
