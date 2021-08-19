package cn.chenyuxian.discuz.mail;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class MailVo {

	/**
	 * 邮件id
	 */
	private String id;
	
	/**
	 * 邮件发送人
	 */
	private String from;
	
	/**
	 * 邮件接收人
	 */
	private String to;
	
	/**
	 * 邮件主题
	 */
	private String subject;
	
	/**
	 * 邮件内容
	 */
	private String text;
	
	/**
	 * 发送时间
	 */
	private Date sentDate;
	
	/**
	 * 抄送
	 */
	private String cc;
	
	/**
	 * 密送
	 */
	private String bbc;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 报错状态
	 */
	private String error;

	@JsonIgnore
	private MultipartFile[] multipartFiles;
	
}
