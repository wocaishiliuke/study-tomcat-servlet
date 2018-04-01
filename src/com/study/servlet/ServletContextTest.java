package com.study.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试ServletContext而创建的Servlet,注册使用注解方式,但servletContext的配置仍在web.xml中配置
 * @author zhouyu
 * @date 2018年2月3日 下午5:16:46
 * 测试路径：http://localhost:8080/study09_tomcat-servlet/servletContextTest
 */
@SuppressWarnings("all")
@WebServlet(
		name="servletContextTest", 
		urlPatterns="/servletContextTest", 
		loadOnStartup=1, 
		initParams={
			@WebInitParam(name="servletConfigHero1",value="黑菜饼"),
			@WebInitParam(name="servletConfigHero2",value="红菜饼")
		})
public class ServletContextTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取servletConfig中的Init参数
		//Enumeration<String> configParameterNames = this.getServletConfig().getInitParameterNames();
		Enumeration<String> configParameterNames = this.getInitParameterNames();
		while (configParameterNames.hasMoreElements()) {
			String configParameterName = configParameterNames.nextElement();
			String configParameterValue = this.getInitParameter(configParameterName);
			System.out.println("servletConfig中的参数：" + configParameterName + " = " + configParameterValue);
		}
		
		//2.获取servletContext中的Init参数
		//ServletContext context = this.getServletConfig().getServletContext();//方式1
		ServletContext context = this.getServletContext();//方式2
		Enumeration<String> contextParameterNames = context.getInitParameterNames();
		while (contextParameterNames.hasMoreElements()) {
			String contextParameterName = contextParameterNames.nextElement();
			String contextParameterValue = context.getInitParameter(contextParameterName);
			System.out.println("servletContext中的参数：" + contextParameterName + " = " + contextParameterValue);
		}
		
		//3.把servletContext当做容器使用,操作attribute参数
		/**
		 * 统计网站访问：只不过每个servlet中都要有这段代码，还会有线程安全问题。
		 * 后续用filter做，这里只是演示servletContext容器作用
		 */
		Integer count = (Integer) context.getAttribute("count");
		if (count == null) {
			count = 1;
		}else {
			count ++;
		}
		resp.setContentType("text/html;charset=utf-8");//这里只能是U8,因为项目编码是U8(跟从浏览器传来的参数不同，要编解码一致)
		resp.getWriter().println("这是第" + count + "次访问...");
		context.setAttribute("count", count);
		
		//4.读取资源文件
		//这里没有/WebContent,发布到Tomcat后会改成项目名
		String aPath = context.getRealPath("/WEB-INF/classes/a.txt");//有时IDE会编译到/build/classes下，但发布的时候还是会拷贝到/WEB-INF/classes下
		String bPath = context.getRealPath("/b.txt");
		String cPath = context.getRealPath("/WEB-INF/c.txt");
		//System.out.println("项目路径为：" + req.getContextPath());//方式1
		System.out.println("项目路径为：" + context.getContextPath());
		System.out.println("a.txt的硬盘(真实)路径为===>" + aPath);
		System.out.println("b.txt的硬盘(真实)路径为===>" + bPath);
		System.out.println("c.txt的硬盘(真实)路径为===>" + cPath);
		System.out.println("a.txt的文件类型为：" + context.getMimeType(aPath));
		System.out.println("b.txt的文件类型为：" + context.getMimeType(bPath));
		System.out.println("c.txt的文件类型为：" + context.getMimeType(cPath));
		//读取文件内容,这里只读a文件的,其他的方式相同
		InputStream in = new FileInputStream(aPath);
		InputStreamReader isr = new InputStreamReader(in,"UTF-8");//这里也只能是U8,因为项目编码是U8		
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();//关一个流即可
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
