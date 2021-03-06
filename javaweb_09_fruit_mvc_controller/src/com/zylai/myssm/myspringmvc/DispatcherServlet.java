package com.zylai.myssm.myspringmvc;

import com.zylai.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/07/15:57
 * @Description: dispatchServlet：中央控制器
 */
// 拦截所有以.do结尾的请求
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private Map<String, Object> beanMap = new HashMap<>();

    public DispatcherServlet() {

    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
//        读取文件的信息
//        1.创建DocumentBuilderFactory对象
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//         2.创建DocumentBuilder
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            3 获取document
            Document document = documentBuilder.parse(inputStream);

//            4 获取所有的bean结点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
//                判断是否是元素结点
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
//                     强转为element来获取id和class
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Class<?> controllerBeanClass = Class.forName(className);
                    Object beanObj = controllerBeanClass.newInstance();

//                    将id和类的实例对象放到map中
                    beanMap.put(beanId, beanObj);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");

//        如果url是：http://localhost:8080/web08/hello.do
//        那么servletPath是： /hello.do
//        思路：
//        1. /hello.do 截取字符串为 hello
//        2. hello -> HelloController

        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1, servletPath.lastIndexOf(".do"));

        Object controllerBeanObj = beanMap.get(servletPath);

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        /*
        * 常见错误：
        * pageNo=2时，
        * */
        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operate.equals(method.getName())) {
                    //1 统一获取请求参数
                    // 获取当前方法的参数数组
                    Parameter[] parameters = method.getParameters();
                    //parameterValues 用来存放参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        //如果参数名是request、response、session 那么就不是通过请求中获取参数的方式了
                        if ("request".equals(parameterName)) {
                            parameterValues[i] = request;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = response;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = request.getSession();
                        } else {
                            // 从请求中获取参数值
                            String parameterValueStr = request.getParameter(parameterName);
                            Object parameterValue = parameterValueStr;
                            //pageNo=2时，传过来的是"2"，而不是2。出现argument type mismatch
                            String typeName = parameter.getType().getName();
                            if(parameterValue!=null & "java.lang.Integer".equals(typeName)){
                                parameterValue = Integer.parseInt(parameterValueStr);
                            }

                            parameterValues[i] = parameterValue;
                        }
                    }
//                2 controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);

//                3. 视图处理
                    String methodReturnStr = (String) returnObj;
//                if redirect
                    if (methodReturnStr.startsWith("redirect:")) {// fz:redirect:fruit.do
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    } else {
//                    fz:edit
                        super.processTemplate(methodReturnStr, request, response);
                    }
                }
            }
//            else{
//                throw new RuntimeException("operate值非法！");
//            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
