# study09_tomcat-servlet
自定义servlet,并完成映射配置,交由servlet container管理；熟悉servlet生命周期方法init,service,destory

## 工程创建

- Preference->server->runtime environment,配置好Local Tomcat
- 创建Dynamic Web Project,生成web.xml,编译后的class文件可放在build/classes或WebContent/WEB-INF/classes(目录不存在时需要手动创建)

## 内容说明

- MyServletTest使用web.xml配置; EclipseServletTest使用eclipse直接创建,使用注解配置;两者都是继承HttpServlet
- ServletLifecycleTest使用注解配置,以实现Servlet接口的方式,完成生命周期函数的重写

## 注意事项
- web.xml/@WebServlet注解中的映射路径属于服务器端,所以绝对路径不需要包含项目名
- 注意全路径匹配和通配符匹配的访问区别

## 要点
### HttpServlet
- 一般自定义与http协议有关的servlet，使用继承HttpServlet的方式，因为该类在Servlet接口基础上添加了一些与HTTP协议相关的处理方法，功能更强大
- HttpServlet抽象类继承自另一抽象类GenericServlet(与协议无关)
- HttpServlet覆写了父类的service(),在该方法中根据请求方式调用不同的doXxx()处理,所以一般只需重写doXxx()，不需要重写service()
- 注册servlet可以使用xml/注解两种配置方式，其中的url-pattern属于服务器端路径，绝对路径中不用包含项目名
- container会提供一个DefaultServlet匹配路径/，当其他所有servlet都匹配不到时，交由该缺省Servlet，如访问Tomcat中的静态图片/HTML
- 该缺省的Servlet在Tomcat的conf/web.xml中配置，任何自定义的servlet如果匹配路径设为/，都会替代DefaultServlet成为缺省Servlet

### ServletConfig
- A servlet configuration object used by a servlet container to pass information to a servlet during initialization
- 一个servletConfig与一个servlet对应：init(ServletConfig config)； 

### ServletContext
- 可作为一个项目级的容器：可以获取init参数，也可以操作普通attribute参数
- 可以获取该项目的项目路径、文件真实(被发布的硬盘)路径、文件类型
- Tomcat支持的文件类型在其配置文件/conf/web.xml中

### 其他
- 该项目中除了d.txt都会发布到Tomcat中，src下的先编译后放入build/classes或WebContent/WEB-INF/classes下(创建项目时配置)
- 发布时，将编译后的WebContent下的内容拷贝到Tomcat的webapps下，然后把WebContent改成项目名