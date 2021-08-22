package cn.chenyuxian.discuz.security.provider;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import lombok.Data;

/**
 * 非对称加密封装类
 *
 * @author chenyuxian
 * @date 2021-08-21
 */
@Data
public class RSA256Key {

	private RSAPublicKey publicKey;

	private RSAPrivateKey privateKey;

}
