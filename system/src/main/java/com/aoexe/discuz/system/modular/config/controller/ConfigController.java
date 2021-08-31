package com.aoexe.discuz.system.modular.config.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.aoexe.discuz.system.config.QiniuConfiguration;
import com.aoexe.discuz.system.modular.config.entity.Config;
import com.aoexe.discuz.system.modular.config.service.IConfigService;
import com.qiniu.common.QiniuException;

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
public class ConfigController {

	@Resource
	private QiniuConfiguration qiniu;

	@Resource
	private IConfigService configService;
	
	@PostMapping("/logo")
	public String logo(@RequestParam("logo") MultipartFile file, @RequestParam("type") String type)
			throws QiniuException, UnsupportedEncodingException, IOException {
		String logoUrl = qiniu.upload(file.getInputStream(), file.getOriginalFilename());
		
		return logoUrl;
		
	}
	
	@DeleteMapping("/logo")
	public String delLogo(@RequestParam("type") String type) {
		
		return "";
	}
	
	@GetMapping("/forum")
	public Map<String, List<Config>> forum() {
		Set<String> tags = configService.getTags();
		Map<String, List<Config>> map = new HashMap<>();
		tags.forEach(t -> map.put(t, configService.getConfigByTag(t)));
		return map;
	}
}
