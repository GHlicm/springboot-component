package com.example.demo.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
	@Autowired
	private MailService mailService;
	
	@Autowired
	private TemplateEngine templateEngine;
	
//	@Test
//	public void testSimpleMail() throws Exception {
//		mailService.sendSimpleMail("444324848@qq.com", "test simple mail", "hello this is a simple mail !");
//	}
//	
//	@Test
//	public void testHtmlMail() throws Exception {
//		String content ="<html>\n"+
//				"<body>\n"+
//				"  <h3>hello world ! 这是一封HTML邮件！</h3>\n"+
//				"<body>\n"+
//				"</html>";
//		mailService.sendHtmlMail("444324848@qq.com", "test html mail ", content);
//	}
	
//	@Test
//	public void testAttachmentMail() {
//		String filePath = "f:\\1.txt";
//		mailService.sendAttachmentsMail("444324848@qq.com", "带附件的邮件", "有邮件请查收!", filePath);
//	}
	
//	@Test
//	public void sendInlineResourceMail() {
//		String rscId = "neo006";
//		String content = "<html><body>这是有图的邮件:<img src=\'cid:" + rscId + "\'></body><html>";
//		String imgPath = "D:\\1.png";
//		
//		mailService.sendInlineResourceMail("444324848@qq.com", "这是有图的邮件", content, imgPath, rscId);
//	}
	
	/**
	 * 使用模板进行HTML邮件的发送
	 */
	@Test
	public void sendTemplateMail() {
		//创建邮件正文
		Context context = new Context();
		context.setVariable("id", "006");
		String emailContent = templateEngine.process("emailTemplate", context);
		
		mailService.sendHtmlMail("444324848@qq.com", "这是模板邮件", emailContent);
	}
}
