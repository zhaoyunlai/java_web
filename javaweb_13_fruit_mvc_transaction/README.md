今日内容：
5. 过滤器Filter
6. 事务管理
7. TransActionManager ThreadLocal  OpenSessionInViewFilter
8. 监听器

## 事务管理
要点：事务管理不能以DAO层的单精度方法为单位，而应该以业务层的方法为单位。

原因：因为一个业务操作会使用到多个dao操作，如果dao1执行成功，dao2失败进行回滚，
虽然这个业务操作失败了，但是dao1还是执行了。

使用OpenSessionInViewFilter，一旦出现错误，放行部分代码会抛出异常，就会被捕获之后进行事务回滚。
难点和解决方法：

- DAO1、DAO2、DAO3这三个组件中的三个操作需要同一个Connection（连接数据库），这样我们才能让这三个操作处于同一个事务中。
  就是使用ThreadLocal 
  
- 如果DAO层出错进行try catch，那么外部就无法捕获这个异常。因此需要在catch中处理之后再抛出一个层级对应的异常，
  比如说，在dao3中出现异常，被dao1中自己的方法捕获，捕获之后再抛出一个DAOException异常。
  随后在DispatcherServlet的try catch中捕获异常，捕获之后再抛出一个DispatcherServletException异常用于
  OpenSessionInViewFilter捕获，这样就可以进行事务的回滚了。
### 保证多个DAO用一个Connection从而实现事务

#### ConnUtil

创建ConnUtil类，将获取connection的方法封装，让事务类和BaseDAO类都调用这个工具类中
的getConn方法，即让所有获取connection的操作都通过这个类实现。

#### TransactionManager

将事务的操作封装在TransactionManager中，这里对于事务操作时都是通过调用`ConnUtil`类中
的getConn方法实现，**这样就保证了对于一个请求，他业务中所有的DAO都是使用的同一个
Connection，实现了事务操作。**

#### OpenSessionInViewFilter

在OpenSessionInViewFilter过滤器中，放行之前开启事务。完成操作之后返回过滤器时再提交事务，
一旦中途有出现错误，就会被捕获进行回滚。

验证:当一个请求过来时，这个请求下调用的所有方法都是使用的同一个connection，
这里以controller中的index方法为例，在index中调用了两个service方法，
我们分别在这两个service方法中打印其connection值进行验证。

###规范化异常

如果在dao层已经进行了try  catch，那么异常在内部都已经被捕获，那么外部不会捕获到异常，
就不会进行事务的回滚操作。所以要实现回滚功能，需要将内部的异常抛出让外部捕获，
即被过滤器进行catch处理。

每一个级别都抛出对应级别的异常，即在自身的try catch中，
在catch的最后再抛出一个异常用于上层的捕获，比如BaseDAO抛出异常DAOException
由DispatcherServlet捕获，DispatcherServlet抛出DispatcherServletException异常
由OpenSessionInViewFilter捕获并进行事务回滚。

### ThreadLocal的原理
常用方法：get()  set(obj)

ThreadLocal称为本地线程，我们可以通过set方法在当前线程上存储数据、通过get方法在当前线程上获取数据。（数据并不是存储在线程上的，只是为了好理解这样说）

set方法源码分析：

```java
public void set(T value) {
    Thread t = Thread.currentThread();// 获取当前线程
    ThreadLocalMap map = getMap(t);  //每一个线程都维护各自的一个容器（ThreadLocalMap）
    if (map != null)
        //一个ThreadLocal只存放一个对象，而一个线程对应的容器可以存放多个ThreadLocal，所以这里map的key值为当前ThreadLocal对象。
        //就比如我们组件中需要共享的对象会有多个（不只connection，就需要多个ThreadLocal进行存储）
        map.set(this, value);
    else
        createMap(t, value);//默认情况下，map没有初始化，第一次往其中添加数据时，再进行初始化
}
```

get源码分析：

```java
public T get() {
    Thread t = Thread.currentThread();// 获取当前线程
    ThreadLocalMap map = getMap(t);//获取和这个线程相关的ThreadLocalMap
    if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);//this指的是当前的ThreadLocal对象，通过它才知道是哪一个工作纽带
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    return setInitialValue();
}
```

### 监听器
将获取配置文件路径和创建IOC容器都放到监听器中执行，而不是放到中央控制器中执行