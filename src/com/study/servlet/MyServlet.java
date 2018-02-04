package com.study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 使用web.xml配置servlet,处理乱码
 * @author zhouyu
 * @date 2018年2月3日 下午4:46:10
 * 测试路径：http://localhost:8080/study09_tomcat-servlet/myServlet/*
 */
@SuppressWarnings("all")
public class MyServlet extends HttpServlet {
	
	//处理get请求：1.form的method="get"; 2.a标签; 3.浏览器直接访问; 4.img的src
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/**
		 * 原因：使用U8编码，浏览器使用GBK解码，所以乱码
		 * 处理：setContentType对内使用U8编码，对外通知浏览器使用U8解码(编解码一致即可，这里都用GBK也可以)
		 */
		//resp.setContentType("text/html;charset=GBK");//处理中文乱码
		resp.setContentType("text/html;charset=utf-8");//处理中文乱码
		resp.getWriter().print("哈哈 白菜饼");
		resp.getWriter().print("MyServlet's doGet...");
		System.out.println("MyServlet的doGet()...");
	}
	
	//处理post请求：1.form的method="post"
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().print("MyServlet's doPost...");
		System.out.println("MyServlet的doPost()...");
	}
	
}



