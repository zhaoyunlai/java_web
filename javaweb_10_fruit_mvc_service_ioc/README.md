今日内容：
1. 再次学习Servlet初始化方法
2. 学习Servlet中的ServletContext和<context-param>
3. 学习业务层
4. IOC
5. 过滤器Filter
6. 事务管理
7. TransActionManager ThreadLocal  OpenSessionInViewFilter

## 1 Servlet初始化方法
见JavaWeb11
## 学习Servlet中的ServletContext和<context-param>
见JavaWeb11


## 3 业务层
## 什么是MVC

MVC：Model（模型）、View（视图）、Controller（控制器）

视图层：用于做视图展示以及和用户交互的一个界面

控制层：能够接收客户端的请求，具体的业务功能还是需要借助于模型组件来完成

模型层：模型分为很多种：有比较简单的pojo，有业务模型组件，有数据访问层组件。

1. pojo/vo：值对象
2. DAO：数据访问对象
3. BO（Business Object）：业务对象

区分业务对象和数据访问对象：

1. DAO中的方法都是单精度方法。什么叫做单精度？一个方法只考虑一个操作，比如添加那就是insert操作，查询就是select操作
2. BO中的方法属于业务方法，实际中的业务是比较复杂的，因此业务方法的粒度是比较粗的

例如：注册这个功能属于业务功能，也就是说注册这个方法属于业务方法

那么这个业务方法中包含了多个DAO方法。也就是说注册这个业务功能需要通过多个DAO方法的组合调用，从而完成注册功能的实现

注册：

1. 检查用户名是否已经被注册  -  DAO中的select操作
2. 向用户表中新增一条新用户记录  -  DAO中的insert操作
3. 向用户积分表新增一条记录（新用户默认初始化积分100分）  - DAO中的insert操作
4. 向系统日志表新增一条记录（某用户在某IP在某个时间注册） -DAO中的insert操作
5. ......

## 4 IOC
之前在servlet中，我们创建service对象，`FruitService fruitService= new FruitServiceImpl();`，
这句话如果出现在servlet中的某个方法内部，那么这个FruitService的作用域（生命周期）应该就是这个方法级别；
如果这句话出现在servlet的类中，也就是说fruitService是一个成员变量，
那么这个fruitService的作用域应该就是这个servlet实例。如果作用域是方法级别，那么可能需要重复创建对象；
如果是实例级别，就会有一些线程不安全的问题；


之后：我们在applicationContext.xml中定义了这个fruitService。然后通过解析xml，构造fruitService实例，
存放在beanMap中，这个beanMap在一个BeanFactory实例中。那么这就说明，每个实例对象都存放在beanMap这个容器中。
因此我们转移了之前的service实例、dao实例等等他们的生命周期。**控制权从程序员转移到BeanFactory，这个现象就叫做控制反转。**


总体思路：将各个bean实例的配置写在`applicationContext.xml`文件中，
在`ClassPathXmlApplication`中创建beanMap，用于存放所有的bean实例（包括所有的controller实例、service实例、DAO实例），
当使用到对应的实例时，就通过getBean()方法获取Bean实例。
