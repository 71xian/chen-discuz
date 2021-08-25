package cn.chenyuxian.discuz.file.utils.minio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.minio.BucketExistsArgs;
import io.minio.DeleteBucketEncryptionArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.RemoveBucketArgs;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;

/**
 * bucket操作类
 *
 * @author chenyuxian
 * @date 2021-08-19
 */
@Component
public class BucketOperation {

	@Autowired(required = false)
	private MinioClient minioClient;

	public List<Bucket> listBuckets() {
		try {
			return minioClient.listBuckets();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> listObjects(String bucketName) {
		return listObjects(bucketName, true, "");
	}
	
	public List<String> listObjects(String bucketName, boolean recursive, String prefix) {
		return listObjects(bucketName, recursive, prefix, 1000);
	}

	public List<String> listObjects(String bucketName, boolean recursive, String prefix,
			int maxKeys) {
		try {
			List<String> list = new ArrayList<>();
			Iterator<Result<Item>> items = minioClient.listObjects(
					ListObjectsArgs.builder()
					.bucket(bucketName)
					.recursive(recursive)
					.prefix(prefix)
					.maxKeys(maxKeys)
					.build()).iterator();
			while(items.hasNext()) {
				list.add(items.next().get().objectName());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean bucketExists(String bucketName) {
		try {
			return minioClient.bucketExists(
					BucketExistsArgs.builder()
					.bucket(bucketName)
					.build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void makeBucket(String bucketName, String region, boolean objectLock) throws Exception {
		if(!bucketExists(bucketName)) {
			minioClient.makeBucket(
		            MakeBucketArgs.builder()
		                .bucket(bucketName)
		                .region(region)
		                .objectLock(objectLock)
		                .build());
		}
	}
	
	public void removeBucket(String bucketName) throws Exception {
		if(bucketExists(bucketName)) {
			minioClient.removeBucket(
					RemoveBucketArgs.builder()
					.bucket(bucketName)
					.build());
		}
	}
	
	public void deleteBucketEncrypt(String bucketName) throws Exception {
		minioClient.deleteBucketEncryption(
				DeleteBucketEncryptionArgs.builder()
				.bucket(bucketName)
				.build());
	}

}
