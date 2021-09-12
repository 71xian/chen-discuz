package com.aoexe.discuz.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aoexe.discuz.system.modular.config.service.IConfigService;

import io.minio.MinioClient;

@Configuration
public class MinIOConfig {
	
	@Autowired
	private IConfigService configService;
	
	@Bean
	public MinioClient minioClient() {
		String accessKey = configService.getValueByKey("minio_access_key");
		String accessSecret = configService.getValueByKey("minio_access_secret");
		return MinioClient.builder()
				.endpoint("1.15.68.129", 9000, false)
				.credentials(accessKey, accessSecret)
				.build();
	}
}
