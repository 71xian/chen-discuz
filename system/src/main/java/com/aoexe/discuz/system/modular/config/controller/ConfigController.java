package com.aoexe.discuz.system.modular.config.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aoexe.discuz.system.modular.config.entity.Config;
import com.aoexe.discuz.system.modular.config.service.IConfigService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-27
 */
@RestController
@RequestMapping("/config")
@Api(tags = "站点配置接口")
public class ConfigController {

	@Resource
	private IConfigService configService;

	@Resource
	private COSClient cosClient;

	private static final String bucket = "img-1300757405";

	private static String key = "";
	
	@Operation(summary = "上传站点Logo")
	@PostMapping("/logo")
	public String uploadLogo(@RequestParam("logo") MultipartFile file, @RequestParam("type") String type)
			throws CosServiceException, CosClientException, IOException {
		key = "/public/" + type + "/" + file.getOriginalFilename();
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(file.getSize());
		objectMetadata.setContentType(file.getContentType());
		PutObjectResult putObjectResult = cosClient.putObject(bucket, key, file.getInputStream(), objectMetadata);
		return putObjectResult.getContentMd5();
	}

	@Operation(summary = "删除站点logo")
	@DeleteMapping("/logo")
	public String delLogo(@RequestParam("type") String type) {
		cosClient.deleteObject(bucket, key);
		return "success";
	}

	@GetMapping("/forum")
	public Map<String, List<Config>> forum() {
		Set<String> tags = configService.getTags();
		Map<String, List<Config>> map = new HashMap<>();
		tags.forEach(t -> map.put(t, configService.getConfigByTag(t)));
		return map;
	}
}
