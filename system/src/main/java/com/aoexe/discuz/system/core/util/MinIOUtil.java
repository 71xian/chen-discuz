package com.aoexe.discuz.system.core.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.Result;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;

@Component
public class MinIOUtil {

	@Autowired
	private MinioClient minioClient;

	public void removeBucket(String bucketName) {
		try {
			minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Bucket> bucketList() {
		try {
			return minioClient.listBuckets();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Iterable<Result<Item>> objectList(String bucketName) {
		try {
			// Lists objects information.
			// Iterable<Result<Item>> results = minioClient.listObjects(
			// ListObjectsArgs.builder().bucket(bucketName).build());

			// Lists objects information recursively.
			Iterable<Result<Item>> results = minioClient
					.listObjects(ListObjectsArgs.builder().bucket(bucketName).recursive(true).build());

			// Lists maximum 100 objects information whose names starts with 'E' and after
			// 'ExampleGuide.pdf'.
//			Iterable<Result<Item>> results = minioClient.listObjects(
//			    ListObjectsArgs.builder()
//			        .bucket(bucketName)
//			        .startAfter("ExampleGuide.pdf")
//			        .prefix("E")
//			        .maxKeys(100)
//			        .build());

			// Lists maximum 100 objects information with version whose names starts with
			// 'E' and after
			// 'ExampleGuide.pdf'.
//			Iterable<Result<Item>> results = minioClient.listObjects(
//			    ListObjectsArgs.builder()
//			        .bucket(bucketName)
//			        .startAfter("ExampleGuide.pdf")
//			        .prefix("E")
//			        .maxKeys(100)
//			        .includeVersions(true)
//			        .build());
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void createBucket(String bucketName) {
		try {
			// Create bucket with default region.
			minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());

//			// Create bucket with specific region.
//			minioClient.makeBucket(
//			    MakeBucketArgs.builder()
//			        .bucket(bucketName)
//			        .region("us-west-1")
//			        .build());
//
//			// Create object-lock enabled bucket with specific region.
//			minioClient.makeBucket(
//			    MakeBucketArgs.builder()
//			        .bucket(bucketName)
//			        .region("us-west-1")
//			        .objectLock(true)
//			        .build());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeObject(String bucketName, String key) {
		try {
			minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(key).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getObject(String bucketName, String key) {
		try {
			// Get presigned URL of an object for HTTP method, expiry time and custom
			// request parameters.
//		String url =
//		    minioClient.getPresignedObjectUrl(
//		        GetPresignedObjectUrlArgs.builder()
//		            .method(Method.DELETE)
//		            .bucket(bucketName)
//		            .object(key)
//		            .expiry(24 * 60 * 60)
//		            .build());
//		System.out.println(url);

			// Get presigned URL string to upload 'my-objectname' in 'my-bucketname'
			// with response-content-type as application/json and life time as one day.
//		Map<String, String> reqParams = new HashMap<String, String>();
//		reqParams.put("response-content-type", "application/json");

//		String url =
//		    minioClient.getPresignedObjectUrl(
//		        GetPresignedObjectUrlArgs.builder()
//		            .method(Method.PUT)
//		            .bucket(bucketName)
//		            .object(key)
//		            .expiry(1, TimeUnit.DAYS)
//		            .extraQueryParams(reqParams)
//		            .build());
//		System.out.println(url);

			// Get presigned URL string to download 'my-objsectname' in 'my-bucketname' and
			// its life time
			// is 2 hours.
			String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET)
					.bucket(bucketName).object(key).expiry(2, TimeUnit.HOURS).build());
			return url;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void uploadObject(MultipartFile file, String bucketName, String key) {
		try {
			minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(key)
					.stream(file.getInputStream(), file.getSize(), 1024 * 1024 * 10L).contentType(file.getContentType())
					.build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
