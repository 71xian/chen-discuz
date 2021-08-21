package cn.chenyuxian.discuz.security.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import cn.chenyuxian.discuz.security.provider.RSA256Key;
import cn.chenyuxian.discuz.security.provider.SecurityUser;
import lombok.extern.slf4j.Slf4j;

/**
 * 加解密工具类
 *
 * @author chenyuxian
 * @date 2021-08-21
 */
@Slf4j
public class RSAUtils {

	private static final String KEY_ALGORITHM = "RSA";

	private static final int KEY_SZIE = 1024;

	private static volatile RSA256Key rsa256Key;

	public static RSA256Key getInstance() throws NoSuchAlgorithmException {
		// 第一次校验：单例模式只需要创建一次实例，若存在实例，不需要继续竞争锁，
		if (rsa256Key == null) {
			// RSA256Key单例的双重校验锁
			synchronized (RSA256Key.class) {
				// 第二次校验：防止锁竞争中自旋的线程，拿到系统资源时，重复创建实例
				if (rsa256Key == null) {
					// 密钥生成所需的随机数源
					KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
					keyPairGen.initialize(KEY_SZIE);
					// 通过KeyPairGenerator生成密匙对KeyPair
					KeyPair keyPair = keyPairGen.generateKeyPair();
					// 获取公钥和私钥
					RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
					RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
					rsa256Key = new RSA256Key();
					rsa256Key.setPublicKey(publicKey);
					rsa256Key.setPrivateKey(privateKey);
				}
			}
		}
		return rsa256Key;
	}
	
	/**
	 * 生成token
	 *
	 * @param user
	 * @return
	 * @throws IllegalArgumentException
	 * @throws NoSuchAlgorithmException
	 * @author chenyuxian
	 * @date 2021-08-21
	 */
	public static String createToken(SecurityUser user) throws IllegalArgumentException, NoSuchAlgorithmException {
		Algorithm algorithm = Algorithm.RSA256(null, getInstance().getPrivateKey());
		Instant now = Instant.now();
		Map<String, SecurityUser> map = new HashMap<>(1);
		return JWT.create()
				.withAudience(user.getUsername())
				.withIssuedAt(Date.from(now))
				.withExpiresAt(Date.from(now.plus(1L, ChronoUnit.DAYS)))
				.withPayload(null)
				.withPayload(map)
				.sign(algorithm);
	}
	
	/**
	 * 检查token是否有效
	 *
	 * @param token
	 * @return
	 * @throws IllegalArgumentException
	 * @throws NoSuchAlgorithmException
	 * @author chenyuxian
	 * @date 2021-08-21
	 */
	public static boolean checkToken(String token) throws IllegalArgumentException, NoSuchAlgorithmException {
		Algorithm algorithm = Algorithm.RSA256(getInstance().getPublicKey(), null);
		JWTVerifier verifier = JWT.require(algorithm)
				.withIssuer("chenyuxian")
				.build();
		try {
			DecodedJWT verify = verifier.verify(token);
			return verify.getExpiresAt().after(new Date());
		}catch (JWTVerificationException e) {
			System.out.println("验证失败");
			return false;
		}
	}
}
