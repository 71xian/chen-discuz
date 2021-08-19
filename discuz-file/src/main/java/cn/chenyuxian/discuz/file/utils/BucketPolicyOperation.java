package cn.chenyuxian.discuz.file.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.minio.GetBucketPolicyArgs;
import io.minio.MinioClient;

/**
 * bucket策略操作类
 *
 * @author chenyuxian
 * @date 2021-08-19
 */
@Component
public class BucketPolicyOperation {

	@Autowired
	private MinioClient minioClient;
	
	public String getBucketPolicy(String bucketName) throws Exception {
		return minioClient.getBucketPolicy(
				GetBucketPolicyArgs.builder()
				.bucket(bucketName)
				.build());
	}
}
