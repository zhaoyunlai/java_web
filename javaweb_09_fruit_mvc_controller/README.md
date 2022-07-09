修改controller：
1. 视图转发：每个方法都会进行资源跳转，因此将其抽离出来，返回字符串。
由中央控制器进行统一的跳转和转发

2. 获取参数：controller中获取参数的代码交由DispatcherServlet完成。
同样是通过反射技术，根据调用的方法来获取其参数列表
   获取即将调用的方法的参数签名信息： `Parameter[] parameters = method.getParameters()`
   通过`parameter.getName()`获取参数的名称；通过`parameter.getType()`获取参数的类型
   准备了`Object[] parameterValues` 这个数组用来存放对应参数的参数值
   另外我们需要考虑参数的类型问题，做类型转换的工作。


review：
1. 最初的做法是：一个请求对应一个Servlet，这样存在的问题是servlet太多了
2. 把一些列的请求都对应一个servlet，IndexServlet、AddServlet、EditServlet等
   通过一个operate的值来确定来决定调用FruitServlet中的哪个方法
   使用的是switch-case方法。对应 项目06
   
3. 在上一个版本中，servlet总充斥着大量的switch-case，随着我们项目的业务规模扩大
   会有很多servlet，会造成代码冗余。所以使用反射技术进行调用，规定operate的值和方法名一致，
   直接根据operate的值来确定调用哪一个方法，如果找不到就抛出异常。对应项目07
   
4. 使用了反射技术之后，还是存在一定的问题：每一个servlet中都存在类似的反射技术代码，因此继续抽取
   设计了中央控制器类：DispatcherServlet。这个类的工作分为两大部分：
   1. 根据url定位到能够处理这个类请求的controller组件：
    1) 根据 从url中提取servletPath：/fruit.do -> fruit  
    2) 根据fruit找到对应的组件：FruitController，这个对应的依据我们存储在applicationContext.xml文件中
       <bean id="fruit" class="com.zylai.fruit.controller.FruitController"/>
       通过DOM技术我们去解析xml文件，在中央控制器中形成一个beanMap容器，用来存放所有的Controller组件
    3) 根据获取到的operate的值定位到我们FruitController中需要调用的方法
    
   2. 调用Controller组件的方法：
    1) 获取参数
       获取即将调用的方法的参数签名信息： `Parameter[] parameters = method.getParameters()`
       通过`parameter.getName()`获取参数的名称；通过`parameter.getType()`获取参数的类型
       准备了`Object[] parameterValues` 这个数组用来存放对应参数的参数值
       另外我们需要考虑参数的类型问题，做类型转换的工作。
    2) 执行方法
       `Object returnObj = method.invoke(controllerBean,parameterValues);`
    3) 视图处理
       将controller中各方法的返回值都改为String类型，返回资源的跳转方式及名称
       例如返回字符串 "redirect:index".
       之后controller只需要进行业务操作，不需要去管获取参数和资源的跳转。都交给中央控制器进行统一的管理
       `String returnStr = (String)returnObj; if (returnStr.startWith("redirect:")){ ...}`
     
如果只是会用框架，对于框架的大体技术原理不了解注定是很底层的码农。