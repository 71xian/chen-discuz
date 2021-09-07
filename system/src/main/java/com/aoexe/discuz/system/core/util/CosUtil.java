package com.aoexe.discuz.system.core.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.exception.MultiObjectDeleteException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.DeleteObjectsRequest;
import com.qcloud.cos.model.DeleteObjectsResult;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ListObjectsRequest;
import com.qcloud.cos.model.ObjectListing;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;

@Component
public class CosUtil {

	@Resource
	private COSClient cosClient;

	public PutObjectResult uploadObject(String bucketName, String key, MultipartFile file)
			throws CosServiceException, CosClientException, IOException {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(file.getSize());
		objectMetadata.setContentType(file.getContentType());
		return cosClient.putObject(bucketName, key, file.getInputStream(), objectMetadata);
	}

	public void deleteObject(String bucketName, String key) {
		cosClient.deleteObject(bucketName, key);
	}

	public void deleteObjects(String bucketName, List<String> keys) {
		DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
		// 设置要删除的key列表, 最多一次删除1000个
		ArrayList<DeleteObjectsRequest.KeyVersion> keyList = new ArrayList<>();
		// 传入要删除的文件名
		keys.forEach(k -> keyList.add(new DeleteObjectsRequest.KeyVersion(k)));
		deleteObjectsRequest.setKeys(keyList);
		// 批量删除文件
		try {
			DeleteObjectsResult deleteObjectsResult = cosClient.deleteObjects(deleteObjectsRequest);
			List<DeleteObjectsResult.DeletedObject> deleteObjectResultArray = deleteObjectsResult.getDeletedObjects();
		} catch (MultiObjectDeleteException mde) { // 如果部分删除成功部分失败, 返回MultiObjectDeleteException
			List<DeleteObjectsResult.DeletedObject> deleteObjects = mde.getDeletedObjects();
			List<MultiObjectDeleteException.DeleteError> deleteErrors = mde.getErrors();
		} catch (CosServiceException e) { // 如果是其他错误，例如参数错误， 身份验证不过等会抛出 CosServiceException
			e.printStackTrace();
			throw e;
		} catch (CosClientException e) { // 如果是客户端错误，例如连接不上COS
			e.printStackTrace();
			throw e;
		}
	}

	public List<String> list(String bucketName, String prefix) {
		List<String> keys = new ArrayList<>();
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
		listObjectsRequest.setBucketName(bucketName);
		listObjectsRequest.setPrefix(prefix);
		listObjectsRequest.setDelimiter("/");
		listObjectsRequest.setMaxKeys(1000);
		ObjectListing objectListing = null;
		do {
			try {
				objectListing = cosClient.listObjects(listObjectsRequest);
			} catch (CosServiceException e) {
				e.printStackTrace();
				return null;
			} catch (CosClientException e) {
				e.printStackTrace();
				return null;
			}
			// common prefix表示表示被delimiter截断的路径, 如delimter设置为/, common prefix则表示所有子目录的路径
			//List<String> commonPrefixs = objectListing.getCommonPrefixes();
			// object summary表示所有列出的object列表
			List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
			keys.addAll(cosObjectSummaries.stream().map(COSObjectSummary::getKey).collect(Collectors.toList()));
			String nextMarker = objectListing.getNextMarker();
			listObjectsRequest.setMarker(nextMarker);
		} while (objectListing.isTruncated());
		return keys;
	}

	public String getObject(String bucketName, String key) {
		GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
		URL url = cosClient.generatePresignedUrl(req);
		return url.toString();
	}
}
