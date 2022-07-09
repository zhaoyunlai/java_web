
### 1 DispatcherServlet
1. DispatcherServlet 

新建DispatcherServlet，称为中央控制器或者核心控制器。
所有的请求都由其处理，根据请求的路径再去调用不同的Servlet

### 2 流程

根据url定位到能够处理这个类请求的controller组件：
大体流程：
1) 根据 从url中提取servletPath：/fruit.do -> fruit  
2) 根据fruit找到对应的组件：FruitController，这个对应的依据我们存储在applicationContext.xml文件中
<bean id="fruit" class="com.zylai.fruit.controller.FruitController"/>
通过DOM技术我们去解析xml文件，在中央控制器中形成一个beanMap容器，用来存放所有的Controller组件
3) 根据获取到的operate的值定位到我们FruitController中需要调用的方法


细节流程：
1. 拦截所有以.do结尾的请求
2. 创建配置文件applicationContext.xml，里面写上所有的请求和
对应的servlet，例如请求fruit.do对应着FruitController
3. 在DispatcherServlet中创建初始化方法，将配置文件中的信息都
存储到一个map<String,Object>中，key为请求的地址，value为对应的servlet（controller）对应的实例化对象
4. 在service方法获取请求地址，根据map中的对应关系调用相应的控制器
5. 具体的控制器根据传递的参数（主要是operate判断使用哪个方法）进行处理

### 小知识点：
xml文件


解决的一个错误不需要过多关注，之后的开发中哦都会避免掉的。
