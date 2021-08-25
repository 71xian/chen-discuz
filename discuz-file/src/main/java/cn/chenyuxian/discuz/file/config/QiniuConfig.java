package cn.chenyuxian.discuz.file.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * 七牛云配置类
 *
 * @author chenyuxian
 * @date 2021-08-22
 */
@org.springframework.context.annotation.Configuration
public class QiniuConfig {

	@Value("${qiniu.access-key}")
	private String accessKey;

	@Value("${qiniu.secret-key}")
	private String secretKey;

	@Value("${qiniu.bucket}")
	public String bucket;
	
	@Value("${qiniu.domain}")
	public String domain;

	private UploadManager uploadManager;
	
	private BucketManager bucketManager;

	private Auth auth;
	
	private StringMap putPolicy;
	
	@PostConstruct
	public void init() {
		auth = Auth.create(accessKey, secretKey);
		Configuration configuration = new Configuration();
		configuration.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
		configuration.resumableUploadMaxConcurrentTaskCount = 2;  // 设置分片上传并发，1：采用同步上传；大于1：采用并发上传
		configuration.useHttpsDomains = false;
		uploadManager = new UploadManager(configuration);
		bucketManager = new BucketManager(auth, configuration);
		putPolicy = new StringMap();
		putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
	}
	
	
	public String getUploadToken(long expireSeconds, StringMap policy) {
		if(policy == null) {
			return auth.uploadToken(bucket, null, expireSeconds, putPolicy);			
		}else {
			return auth.uploadToken(bucket, null, expireSeconds, policy);	
		}
	}

	public UploadManager getUploadManager() {
		return uploadManager;
	}

	public BucketManager getBucketManager() {
		return bucketManager;
	}
	
	public String privateDownloadUrl(String publicUrl, long expireInSeconds) {
		return auth.privateDownloadUrl(publicUrl, expireInSeconds);
	}
	
}
