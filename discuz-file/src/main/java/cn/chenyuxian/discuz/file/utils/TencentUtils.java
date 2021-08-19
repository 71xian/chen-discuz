/*
 * package cn.chenyuxian.discuz.file.utils;
 * 
 * import java.util.List;
 * 
 * import com.qcloud.cos.COSClient; import com.qcloud.cos.ClientConfig; import
 * com.qcloud.cos.auth.BasicCOSCredentials; import
 * com.qcloud.cos.auth.COSCredentials; import
 * com.qcloud.cos.exception.CosClientException; import
 * com.qcloud.cos.exception.CosServiceException; import
 * com.qcloud.cos.http.HttpProtocol; import com.qcloud.cos.model.Bucket; import
 * com.qcloud.cos.model.COSObjectSummary; import
 * com.qcloud.cos.model.ListObjectsRequest; import
 * com.qcloud.cos.model.ObjectListing; import com.qcloud.cos.region.Region;
 * 
 * public class TencentUtils {
 * 
 * private static String secretId = "AKIDCCwUosfAON5XieKkunPkob7MQEl10iAN";
 * 
 * private static String secretKey = "TgcHfmcDYcQzGH1UhdKktlMp66pGRLyJ";
 * 
 * private static String bucketName = "discuzq-1300757405";
 * 
 * private static String bucketRegion = "ap-nanjing";
 * 
 * public static void main(String[] args) { COSCredentials cred = new
 * BasicCOSCredentials(secretId, secretKey); // 2 设置 bucket 的地域, COS 地域的简称请参照
 * https://cloud.tencent.com/document/product/436/6224 // clientConfig 中包含了设置
 * region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。 Region
 * region = new Region(bucketRegion); ClientConfig clientConfig = new
 * ClientConfig(region); // 这里建议设置使用 https 协议
 * clientConfig.setHttpProtocol(HttpProtocol.https); // 3 生成 cos 客户端。 COSClient
 * cosClient = new COSClient(cred, clientConfig);
 * 
 * List<Bucket> buckets = cosClient.listBuckets(); for (Bucket bucket : buckets)
 * { String bucketName = bucket.getName(); String bucketLocation =
 * bucket.getLocation(); System.out.println(bucketName + "," + bucketLocation);
 * }
 * 
 * // File localFile = new
 * File("C:\\Users\\chenyuxian\\Desktop\\spring-security-reference.pdf"); //
 * String key = localFile.getName(); // PutObjectRequest putObjectRequest = new
 * PutObjectRequest(bucketName, key, localFile); // PutObjectResult
 * putObjectResult = cosClient.putObject(putObjectRequest);
 * 
 * ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
 * listObjectsRequest.setBucketName(bucketName);
 * listObjectsRequest.setPrefix("/"); listObjectsRequest.setDelimiter("/");
 * listObjectsRequest.setMaxKeys(100000); ObjectListing objectListing = null; do
 * { try { objectListing = cosClient.listObjects(listObjectsRequest); } catch
 * (CosServiceException e) { e.printStackTrace(); return; } catch
 * (CosClientException e) { e.printStackTrace(); return; } // common
 * prefix表示表示被delimiter截断的路径, 如delimter设置为/, common prefix则表示所有子目录的路径
 * List<String> commonPrefixs = objectListing.getCommonPrefixes(); // object
 * summary表示所有列出的object列表 List<COSObjectSummary> cosObjectSummaries =
 * objectListing.getObjectSummaries(); for (COSObjectSummary cosObjectSummary :
 * cosObjectSummaries) { // 文件的路径key String fileKey = cosObjectSummary.getKey();
 * // 文件的etag String etag = cosObjectSummary.getETag(); // 文件的长度 long fileSize =
 * cosObjectSummary.getSize(); // 文件的存储类型 String storageClasses =
 * cosObjectSummary.getStorageClass();
 * 
 * System.out.println(fileKey + "," + etag + "," + storageClasses); } String
 * nextMarker = objectListing.getNextMarker();
 * listObjectsRequest.setMarker(nextMarker); } while
 * (objectListing.isTruncated());
 * 
 * } }
 */