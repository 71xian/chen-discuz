package cn.chenyuxian.discuz.core.util;

import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * Jwt生成
 *
 * @author chenyuxian
 * @date 2021-08-16
 */
@Slf4j
public class JwtUtil {

	/**
	 * 私匙
	 */
	private final static String BASE_64_SECRET = "www.chenyuxian.cn";
	
	/**
	 * 过期时间
	 */
	private final static int EXPIRES_SECOND = 1000 * 60 * 2 * 60;
	
	public static Claims parseJwt(String jwt) {
		log.info("解析jwt: {}", jwt); 
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(BASE_64_SECRET))
					.parseClaimsJws(jwt).getBody();
			return claims;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String generateJwt(String useOpenId, Long userId) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
		
		long nowMills = System.currentTimeMillis();
		return null;
	}
}
