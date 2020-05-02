package com.li.controller;

import com.li.utils.ThymelafUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Controller
public class demoTest {
	@Autowired
	private TemplateEngine templateEngine;

	@ResponseBody
	@RequestMapping(value="/test")
	public String test(){
		return "test!!!";
	}

	@ResponseBody
	@RequestMapping(value="/{skuId}.html")
	public String getHtml(@PathVariable("skuId") String skuId, Model model){
		Map<String, Object> variables = new HashMap();
		variables.put("skuInfo", skuId);

		String dirPath = "D:/";
		String filePath = dirPath + "test.html"; //设置路径
		String templatePage = "toHtmlInfo";	//生成页面的模板页面地址

		ThymelafUtils.createHtmlPage(variables, dirPath, filePath,
				templatePage, templateEngine);

		return "toHtmlInfo success !";
	}

}
