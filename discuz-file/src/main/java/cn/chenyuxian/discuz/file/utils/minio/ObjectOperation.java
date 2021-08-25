package cn.chenyuxian.discuz.file.utils.minio;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.minio.CopyObjectArgs;
import io.minio.CopySource;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.Result;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;

/**
 * 对象操作类
 *
 * @author chenyuxian
 * @date 2021-08-19
 */
@Component
public class ObjectOperation {

	@Autowired(required = false)
	private MinioClient minioClient;
	
	public void putObject(String bucketName, String objectName, InputStream inputStream, String contenntType) throws Exception {
		minioClient.putObject(
				PutObjectArgs.builder()
				.bucket(bucketName)
				.object(objectName)
				.stream(inputStream, inputStream.available(), -1)
				.contentType(contenntType)
				.build());
	}
	
	public InputStream getObject(String bucketName, String objectName) throws Exception {
		return minioClient.getObject(
				GetObjectArgs.builder()
				.bucket(bucketName)
				.object(objectName)
				.build());
	}
	
	public InputStream getPartialObject(String bucketName, String objectName, long offset, long length) throws Exception {
		return minioClient.getObject(
				GetObjectArgs.builder()
				.bucket(bucketName)
				.object(objectName)
				.offset(offset)
				.length(length)
				.build());
	}
	
	public void removeObject(String bucketName, String objectName) throws Exception {
		minioClient.removeObject(
				RemoveObjectArgs.builder()
				.bucket(bucketName)
				.object(objectName)
				.bypassGovernanceMode(true)
				.build());
	}
	
	public void removeObjects(String bucketName, List<DeleteObject> objects) throws Exception {
		Iterable<Result<DeleteError>> results = minioClient.removeObjects(
				RemoveObjectsArgs.builder()
				.bucket(bucketName)
				.objects(objects)
				.build());
		for (Result<DeleteError> result : results) {
			DeleteError error = result.get();
			System.out.println(
		            "Error in deleting object " + error.objectName() + "; " + error.message());
		}
	}
	
	public StatObjectResponse statObject(String bucketName, String objectName) throws Exception {
		return minioClient.statObject(
				StatObjectArgs.builder()
				.bucket(bucketName)
				.object(objectName)
				.build());
	}
	
	public void copyObject(String bucketName, String sourceBucketName, String objectName) throws Exception {
		minioClient.copyObject(
				CopyObjectArgs.builder()
				.bucket(bucketName)
				.object(objectName)
				.source(
						CopySource.builder()
						.bucket(sourceBucketName)
						.object(objectName)
						.build())
				.build());
	}
}
