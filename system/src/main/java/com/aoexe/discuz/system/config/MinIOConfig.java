package com.aoexe.discuz.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aoexe.discuz.system.modular.setting.service.ISettingsService;

import io.minio.MinioClient;

@Configuration
public class MinIOConfig {

	@Autowired
	private ISettingsService settingsService;

	@Bean
	public MinioClient minioClient() {
		String accessKey = settingsService.getMinIOAccessKey();
		String accessSecret = settingsService.getMinIOSecret();
		return MinioClient.builder().endpoint("42.192.191.157", 9000, false).credentials(accessKey, accessSecret).build();
	}
}
