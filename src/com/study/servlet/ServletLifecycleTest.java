package com.study.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
/**
 * 使用Eclipse自动生成的Servlet,测试其生命周期方法
 * @author zhouyu
 * @date 2018年2月3日 下午4:48:33
 * 测试路径：http://localhost:8080/study09_tomcat-servlet/servletLifecycleTest
 */
@WebServlet(description="测试servlet的声明周期方法", urlPatterns="/servletLifecycleTest", loadOnStartup=1)
public class ServletLifecycleTest implements Servlet {
	
	//初始化
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("servlet的init()...");
	}
	//提供服务
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("servlet的service()...");
	}
	//销毁(在eclipse的server窗口中关闭才是正常关闭，从console中关闭服务不会走这里的destroy())
	@Override
	public void destroy() {
		System.out.println("servlet的destroy()...");
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
