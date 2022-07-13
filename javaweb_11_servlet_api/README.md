### 1 Servlet初始化方法
Servlet生命周期：实例化、初始化、服务、销毁
Servlet中初始化方法有两个（在GenericServlet类中）：init(),init(config)
其中带参数的方法为：
public void init(ServletConfig config){
this.config=config;
init();
}
另外一个无参的init方法如下：
public void init() throws ServletException {
}
因此想要在servlet初始化时做一些准备工作，可以写在init中
1. 获取config对象：ServletConfig config = getServletConfig();
2. 获取初始化参数值：config.getInitParameter(key);
3. 在web.xml文件中配置Servlet
4. 也可以通过注解的方法配置
   ```
   @WebServlet(urlPatterns = {"/demo01"},
   initParams = {
   @WebInitParam(name = "hello1",value = "world1"),
   @WebInitParam(name="uname1",value="jack1")
   })
   ```

### 学习Servlet中的ServletContext和<context-param>

1）获取ServletContext的方法
1. 在初始化方法中： ServletContext servletContext = getServletContext();
2. 在服务方法中也可以通过request对象获取，也可以通过session获取
   request.getServletContext();
   request.getSession().getServletContext();

2）获取初始化值:String value = servletContext.getInitParameter("key");

