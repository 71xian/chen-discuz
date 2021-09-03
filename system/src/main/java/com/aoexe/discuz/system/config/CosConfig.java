package com.aoexe.discuz.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;

/**
 * 腾讯云存储配置
 * 
 * @author chenyuxian
 * @date 2021-09-03 18:31:24
 */
@Configuration
public class CosConfig {

	@Bean
	public COSClient cosClient() {
		
		COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
		Region region = new Region("ap-guangzhou");
		ClientConfig clientConfig = new ClientConfig(region);
		clientConfig.setHttpProtocol(HttpProtocol.https);
		return new COSClient(cred, clientConfig);
	}
}
