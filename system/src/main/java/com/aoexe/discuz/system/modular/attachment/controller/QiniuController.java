package com.aoexe.discuz.system.modular.attachment.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aoexe.discuz.core.annotion.Permission;
import com.aoexe.discuz.system.config.QiniuConfiguration;
import com.qiniu.common.QiniuException;

/**
 * 使用七牛云操作文件
 *
 * @author chenyuxian
 * @date 2021-08-22
 */
@RestController
@RequestMapping("/qiniu")
@Permission
public class QiniuController {

	@Autowired
	private QiniuConfiguration utils;

	/**
	 * 上传文件
	 *
	 * @param file
	 * @return
	 * @throws QiniuException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @author chenyuxian
	 * @date 2021-08-27
	 */
	@PostMapping("/upload")
	@Permission
	public Map<String, String> upload(@RequestParam("file") MultipartFile file)
			throws QiniuException, UnsupportedEncodingException, IOException {
		return new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;

			{
				put("url", utils.upload(file.getInputStream(), file.getOriginalFilename()));
			}
		};
	}

	/**
	 * 获取下载链接
	 *
	 * @param key
	 * @param expired
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws QiniuException
	 * @author chenyuxian
	 * @date 2021-08-27
	 */
	@GetMapping("/download")
	@Permission
	public String download(@RequestParam("key") String key)
			throws UnsupportedEncodingException, QiniuException {
		return utils.download(key);
	}

	@GetMapping("/bucket")
	public List<String> lists(@RequestParam("prefix") String prefix) {
		return utils.lists(prefix);
	}

}
