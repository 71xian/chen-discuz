package cn.chenyuxian.discuz.security.provider;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装RSA256属性
 *
 * @author chenyuxian
 * @date 2021-08-21
 */
@Getter
@Setter
public class RSA256Key {

	private RSAPublicKey publicKey;

	private RSAPrivateKey privateKey;

	public RSA256Key() {

	}

	public RSA256Key(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
		super();
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

}
