package com.aoexe.discuz.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aoexe.discuz.system.core.util.MinIOUtil;
import com.aoexe.discuz.system.modular.config.model.result.ConfigInfo;
import com.aoexe.discuz.system.modular.config.model.result.SiteInfo;
import com.aoexe.discuz.system.modular.config.service.IConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-27
 */
@RestController
@Api(value = "站点配置", tags = "站点配置")
public class ConfigController {

	@Autowired
	private IConfigService configService;

	@Autowired
	private MinIOUtil util;
	
	private String bucketName = "discuz";

	private static String key = "";

	@GetMapping("/forum")
	@ApiOperation(value = "获取配置", notes = "获取配置")
	public ConfigInfo configInfo() {
		return null;
	}
	
	@GetMapping("/siteinfo")
	@ApiOperation(value = "站点基本信息", notes = "站点基本信息")
	public SiteInfo siteInfo() {
		return null;
	}

	@PostMapping("/config/logo")
	@ApiOperation(value = "上传站点logo", notes = "上传站点logo")
	public String uploadLogo(@RequestParam("logo") MultipartFile file, @RequestParam("type") String type) {
		key = "/public/" + type + "/" + file.getOriginalFilename();
		util.uploadObject(file, bucketName, key);
		return key;
	}

	@DeleteMapping("/config/logo")
	@ApiOperation(value = "删除站点logo", notes = "删除站点logo")
	public void deleteLogo(@RequestParam("type") String type) {
		util.removeObject(bucketName, key);
	}

	@GetMapping("/check")
	@ApiOperation(value = "检查更新", notes = "检查更新")
	public void check() {

	}

}
