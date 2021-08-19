package cn.chenyuxian.discuz.mail;

import java.util.HashMap;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailService {

	@Autowired
	private MailProperties mailProperties;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;

	public void sendMail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailProperties.getUsername());
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);
	}
	
	public void sendResume() {
		MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("root@chenyuxian.cn", "知乎");
            helper.setTo("2586846075@qq.com");
            helper.setSubject("简历投递提醒");

            // 定义模板数据
            Context context = new Context();
            HashMap<String, Object> paramMap = new HashMap<>(16);
            paramMap.put("candidate", "bravo1988");
            paramMap.put("sex", 1);
            paramMap.put("publisher", "马云");
            paramMap.put("jobName", "Java高级开发工程师(saas方向)");
            paramMap.put("icon", "https://pic4.zhimg.com/v2-1907eb21be63d35b077e6ed3cbcbfe13_xll.jpg");
            paramMap.put("school", "清华大学");
            paramMap.put("major", "计算机科学与技术");
            paramMap.put("education", "本科");
            paramMap.put("status", "已离职");
            paramMap.put("expectJob", "软件工程师");
            paramMap.put("expectCity", "杭州");
            paramMap.put("salaryBegin", 10);
            paramMap.put("salaryEnd", 12);
            paramMap.put("appendixUrl", "https://wise-job.oss-cn-zhangjiakou.aliyuncs.com/wiseJob/1601035929262.pdf");
            paramMap.put("appendixName", "bravo1988_Java.pdf");
            context.setVariables(paramMap);
            // 获取thymeleaf模板，填充数据
            String emailContent = templateEngine.process("mail", context);
            helper.setText(emailContent, true);
            // 发送填充好的整个html页面
            mailSender.send(message);
            log.info("简历投递提醒发送成功。");
        } catch (Exception e) {
            log.error("简历投递提醒发送失败！", e);
        }
	}
	
}
