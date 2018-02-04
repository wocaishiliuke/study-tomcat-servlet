package com.study.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试ServletConfig而创建的Servlet,使用xml配置方式完成该servlet的注册
 * @author zhouyu
 * @date 2018年2月3日 下午4:33:29
 * 测试路径：http://localhost:8080/study09_tomcat-servlet/servletConfigTest
 */
@SuppressWarnings("all")
public class ServletConfigTest extends HttpServlet {
	/**
	 * 完成servletConfig中参数的获取
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = this.getServletConfig();
		//System.out.println("key = hero1" + "; value = " + config.getInitParameter("hero1"));
		Enumeration<String> names = config.getInitParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = config.getInitParameter(name);
			System.out.println("name = " + name + "; value = " + value);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
