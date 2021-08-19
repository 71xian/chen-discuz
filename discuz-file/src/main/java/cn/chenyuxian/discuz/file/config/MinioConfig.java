package cn.chenyuxian.discuz.file.config;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

/**
 * MinIO配置类
 *
 * @author chenyuxian
 * @date 2021-08-17
 */
@Configuration
public class MinioConfig {

	@Value("${minio.accessKey}")
	private String accessKey;

	@Value("${minio.secretKey}")
	private String secretKey;

	@Value("${minio.endpoint}")
	private String endpoint;
	
	@Value("${minio.port}")
	private Integer port;
	
	@Bean
	public MinioClient minioClient() throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		return MinioClient.builder()
				.endpoint(endpoint, port, false)
				.credentials(accessKey, secretKey)
				.build();
	}
}
