package com.li.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ThymelafUtils {

    /**
     * 生成页面的方法
     * @param variables 传入参数
     * @param dirPath   文件目录地址
     * @param filePath  文件地址路径
     * @param templatePage 模板页面
     * @param templateEngine 模板引擎
     */
    public static void createHtmlPage(Map variables, String dirPath, String filePath,
                                      String templatePage, TemplateEngine templateEngine){

        Context context = new Context();
        context.setVariables(variables);
        if (!new File(dirPath).exists()){
            new File(dirPath).mkdirs();
        }
        // 创建输出流，关联到一个临时文件
        File temp = new File(filePath);
        try (PrintWriter writer = new PrintWriter(temp, "UTF-8")) {
            // 利用thymeleaf模板引擎生成 静态页面
            templateEngine.process(templatePage, context, writer);
        } catch (IOException e) {
            System.out.println("页面静态化出错：{}" + e.getMessage());
        }
    }

}
