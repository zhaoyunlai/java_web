### 1 设置编码

1）post模式下设置编码，防止中文乱码
  需要注意的是，设置编码必须在所有获取参数之前执行
  request.setCharacterEncoding("UTF-8");

2）get方式目前不需要设置编码（Tomcat8及其以后）

### 2 Servlet的继承关系 - 重点查看service服务方法
#### 2.1继承关系
 javax.servlet.Servlet接口
    javax.servlet.GenericServlet抽象类 
        javax.servlet.http.HttpServlet抽象类
#### 2.2相关方法

javax.servlet.Servlet接口：
- void init(config) 初始化方法
- void service(request,response) 服务方法
  当服务端发送请求时，会自动调用服务方法
- void destroy()  销毁方法

javax.servlet.GenericServlet抽象类：
- void service(request,response)仍然是抽象的
javax.servlet.http.HttpServlet抽象类
- void service(request,response)不是抽象的
    1. String method = req.getMethod();获取请求的方式
    2.  各种if判断，根据请求方式不同，决定去调用不同的do方法
        ```
        例如：
        } else if (method.equals("POST")) {
            this.doPost(req, resp);
        } else if (method.equals("PUT")) {
            this.doPut(req, resp);
        } else if (method.equals("DELETE")) {
            this.doDelete(req, resp);
        } else if (method.equals("OPTIONS")) {
            this.doOptions(req, resp);
        } else if (method.equals("TRACE")) {
            this.doTrace(req, resp);
        ```
    3. 在HttpServlet这个抽象类中，do方法都差不多
    ```
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = lStrings.getString("http.method_post_not_supported");
        this.sendMethodNotAllowed(req, resp, msg);
    }
  ```
  #### 2.3小结：
  1. 继承关系：HttpServlet -> GenericServlet -> Servlet
  2. Servlet中的核心方法：init(),service(),destroy()
  3. 服务方法：当有请求过来时，service方法会自动响应（其实是Tomcat容器调用的）
     在HttpServlet中我们回去分析请求的方式：到底是get、post等等，然后再决定
     调用的是哪个do开头的方法，
     在HttpServlet中这些do方法默认都是405的实现风格，即需要子类去实现对应的方法，
     否则默认会报405错误
  4. 因此，我们在新建Servlet时，我们才回去考虑请求方法，从而决定重写哪个do方法

### 3 Servlet的生命周期

    1) 生命周期：从出生到死亡的过程就是生命周期。对应servlet中的三个方法：init(),srvice(),destroy();
    2）默认情况下：
        第一次接收请求时，这个servlet会进行实例化（调用构造方法）、初始化(调用init()）、然后服务（调用service）
        从第二次请求开示，每一次都是服务
        当容器关闭时，其中的所有的servlet实例会被销毁，调用销毁方法

### 4 Http协议

### 5 会话

### 6 Thymeleaf

