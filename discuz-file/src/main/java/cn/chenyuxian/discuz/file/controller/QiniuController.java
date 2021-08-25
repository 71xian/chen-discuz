package cn.chenyuxian.discuz.file.controller;

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

import com.qiniu.common.QiniuException;

import cn.chenyuxian.discuz.file.utils.qiniu.QiniuUtils;

/**
 * 使用七牛云操作文件
 *
 * @author chenyuxian
 * @date 2021-08-22
 */
@RestController
@RequestMapping("/qiniu")
public class QiniuController {

	@Autowired
	private QiniuUtils utils;

	@PostMapping("/upload")
	public Map<String, String> upload(@RequestParam("file") MultipartFile file)
			throws QiniuException, UnsupportedEncodingException, IOException {
		return new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;

			{
				put("url", utils.upload(file.getInputStream(), file.getOriginalFilename()));
			}
		};
	}

	@GetMapping("/download")
	public Map<String, String> download(@RequestParam("key") String key, @RequestParam("expired") Long expired)
			throws UnsupportedEncodingException, QiniuException {
		return utils.download(key, expired);
	}

	@GetMapping("/bucket")
	public List<String> lists(@RequestParam("prefix") String prefix) {
		return utils.lists(prefix);
	}

}
