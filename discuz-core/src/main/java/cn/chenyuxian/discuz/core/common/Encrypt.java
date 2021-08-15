package cn.chenyuxian.discuz.core.common;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class Encrypt {

	public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
		KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
		
		// 生成RSA公钥/私匙
		kpGen.initialize(1024);
		KeyPair kp = kpGen.generateKeyPair();
		PrivateKey sk = kp.getPrivate();
		PublicKey pk = kp.getPublic();
		System.out.println(sk);
		
		byte[] message = "hello, I am bob!".getBytes(StandardCharsets.UTF_8);
		Signature s = Signature.getInstance("SHA1withRSA");
		s.initSign(sk);
		s.update(message);
		byte[] signed = s.sign();
		System.out.println(String.format("signature: %x", new BigInteger(1, signed)));
		
		Signature v = Signature.getInstance("SHA1withRSA");
		v.initVerify(pk);
		v.update(message);
		boolean valid = v.verify(signed);
		System.out.println(valid);
	}
}
