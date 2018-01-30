# study09_tomcat-servlet
自定义servlet,并完成映射配置,交由servlet container管理；熟悉servlet生命周期

## 工程创建

- Preference->server->runtime environment,配置好Local Tomcat
- 创建Dynamic Web Project,生成web.xml,编译后的class文件可放在build/classes或WebContent/WEB-INF/classes(目录不存在时需要手动创建)

## 内容说明

- MyServletTest使用web.xml配置; EclipseServletTest使用eclipse直接创建,使用注解配置;两者都是继承HttpServlet
- ServletLifecycleTest使用注解配置,以实现Servlet接口的方式,完成生命周期函数的重写

## 注意事项
- web.xml/@WebServlet注解中的映射路径属于服务器端,所以绝对路径不需要包含项目名
- 注意全路径匹配和通配符匹配的访问区别