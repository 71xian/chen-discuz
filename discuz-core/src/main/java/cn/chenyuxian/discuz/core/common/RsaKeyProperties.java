package cn.chenyuxian.discuz.core.common;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.chenyuxian.discuz.core.util.RsaUtils;
import lombok.Data;

@Data
@Component
public class RsaKeyProperties {

	@Value(value = "${rsa.key.publicFile}")
	private String publicFile;
	
	@Value(value = "${rsa.key.privateFile}")
	private String privateFile;

	private PublicKey publicKey;
	
	private PrivateKey privateKey;

	public RsaKeyProperties() {
		try {
			createRsaKey();
		} catch (Exception e) {
			System.out.println(publicFile);
			System.out.println(privateFile);
			System.out.println("文件不存在");
		}
	}
	
	private void createRsaKey() throws Exception {
		publicKey = RsaUtils.getPublicKey(publicFile);
		privateKey = RsaUtils.getPrivateKey(privateFile);
	}


}
