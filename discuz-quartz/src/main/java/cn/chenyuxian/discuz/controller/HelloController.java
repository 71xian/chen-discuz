package cn.chenyuxian.discuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.chenyuxian.discuz.mail.MailService;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	private MailService mailService;
	
	@GetMapping("/mail")
	public String sendMail() {
		mailService.sendResume();
		return "hello";
	}
}
