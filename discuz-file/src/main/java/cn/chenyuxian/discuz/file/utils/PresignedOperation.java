package cn.chenyuxian.discuz.file.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;

/**
 * 外链操作类
 *
 * @author chenyuxian
 * @date 2021-08-19
 */
@Component
public class PresignedOperation {

	@Autowired
	private MinioClient minioClient;

	public String presignedGetObject(String bucketName, String objectName) throws Exception {
		return minioClient.getPresignedObjectUrl(
				GetPresignedObjectUrlArgs.builder()
				.method(Method.GET)
				.bucket(bucketName)
				.object(objectName)
				.expiry(7, TimeUnit.DAYS)
				.build());
	}
	
	public String presignedPutObject(String bucketName, String objectName) throws Exception {
		return minioClient.getPresignedObjectUrl(
				GetPresignedObjectUrlArgs.builder()
				.method(Method.PUT)
				.bucket(bucketName)
				.object(objectName)
				.expiry(7, TimeUnit.DAYS)
				.build());
	}
}
