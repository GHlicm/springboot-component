package com.example.demo.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.fromMail.addr}")
	private String from;
	
	/**
	 * 发送普通的邮件
	 */
	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		
		try {
			mailSender.send(message);
			logger.info("简单的邮件已经发送!");
		}catch(Exception e) {
			logger.error("发送简单邮件发生异常!", e);
		}
	}
	
	/**
	 * 发送HTML页面的邮件
	 */
	@Override
	public void sendHtmlMail(String to, String subject, String content) {
		// TODO Auto-generated method stub
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			//true表示需要创建一个multipart 
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			
			mailSender.send(message);
			logger.info("html邮件发送成功!");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			logger.error("发送html邮件时发生异常！", e);
		}
	}
	
	/**
	 * 发送带附件的邮件
	 */
	public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);
			
			mailSender.send(message);
			logger.info("邮件已经成功发送");
		}catch(Exception e) {
			logger.error("邮件发送异常 ！", e);
		}
	}
	
	/**
	 * 发送带静态资源的邮件
	 */
	@Override
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
		// TODO Auto-generated method stub
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			
			FileSystemResource res = new FileSystemResource(new File(rscPath));
			helper.addInline(rscId, res);
			
			mailSender.send(message);
			logger.info("嵌入静态资源的邮件已经发送！");
		}catch(MessagingException e) {
			logger.error("发送嵌入静态资源的邮件时发生异常！", e);
		}
	}
	

	
}
