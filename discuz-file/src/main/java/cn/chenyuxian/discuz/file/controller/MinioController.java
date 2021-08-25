package cn.chenyuxian.discuz.file.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.chenyuxian.discuz.file.utils.minio.BucketOperation;
import cn.chenyuxian.discuz.file.utils.minio.ObjectOperation;
import cn.chenyuxian.discuz.file.utils.minio.PresignedOperation;

/**
 * 使用minio操作文件
 *
 * @author chenyuxian
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/minio")
public class MinioController {

	@Autowired
	private BucketOperation bucketOperation;

	@Autowired
	private ObjectOperation objectOperation;

	@Autowired
	private PresignedOperation presignedOperation;

	/**
	 * 获取对象信息
	 *
	 * @param bucket
	 * @param object
	 * @return
	 * @throws Exception
	 * @author chenyuxian
	 * @date 2021-08-19
	 */
	@GetMapping("/object")
	public String getObjectInfo(@RequestParam("bucket") String bucket, @RequestParam("name") String object)
			throws Exception {
		return objectOperation.statObject(bucket, object).toString();
	}

	/**
	 * 获取对象外链
	 *
	 * @param bucket
	 * @param object
	 * @return
	 * @throws Exception
	 * @author chenyuxian
	 * @date 2021-08-19
	 */
	@GetMapping("/object/link")
	public String getObjectURL(@RequestParam("bucket") String bucket, @RequestParam("name") String object)
			throws Exception {
		return presignedOperation.presignedGetObject(bucket, object);
//		return presignedOperation.presignedPutObject(bucket, object);
	}

	/**
	 * 删除对象
	 *
	 * @param bucket
	 * @param object
	 * @throws Exception
	 * @author chenyuxian
	 * @date 2021-08-19
	 */
	@DeleteMapping("/object")
	public void delObject(@RequestParam("bucket") String bucket, @RequestParam("name") String object) throws Exception {
		objectOperation.removeObject(bucket, object);
	}

	/**
	 * 上传对象
	 *
	 * @param object
	 * @param bucketName
	 * @return
	 * @throws Exception
	 * @author chenyuxian
	 * @date 2021-08-19
	 */
	@PostMapping("/object")
	public String putObject(@RequestParam("name") MultipartFile[] objects, @RequestParam("bucket") String bucketName)
			throws Exception {
		for(MultipartFile object : objects) {
			String file = object.getOriginalFilename();
			String suffix = file.split("\\.")[1];
			switch (suffix) {
			case "jpg":
				suffix = "jpeg";
			case "gif":
			case "png":
				suffix = "image/" + suffix;
				break;
			case "pdf":
				suffix = "application/pdf";
				break;
			case "mp4":
				suffix = "video/mp4";
				break;
			default:
				suffix = "application/octet-stream";
				break;
			}
			String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
			objectOperation.putObject(bucketName, date + file, object.getInputStream(), suffix);
		}
		return "success";
	}

	/**
	 * 获取所有匹配前缀对象
	 *
	 * @param bucket
	 * @param prefix
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-17
	 */
	@GetMapping("/object/prefix")
	public List<String> getObjectsPrefix(@RequestParam("bucket") String bucket,
			@RequestParam("prefix") String prefix) {
		return bucketOperation.listObjects(bucket, true, prefix);
	}
	
	/**
	 * 新建存储桶
	 *
	 * @param bucket
	 * @author chenyuxian
	 * @throws Exception
	 * @date 2021-08-17
	 */
	@PostMapping("/bucket")
	public void createBucket(@RequestParam("bucket") String bucket) throws Exception {
		bucketOperation.deleteBucketEncrypt(bucket);
	}

	/**
	 * 删除存储桶
	 *
	 * @param bucket
	 * @author chenyuxian
	 * @throws Exception
	 * @date 2021-08-17
	 */
	@DeleteMapping("/bucket")
	public void delBucket(@RequestParam("bucket") String bucket) throws Exception {
		bucketOperation.removeBucket(bucket);
	}

}
