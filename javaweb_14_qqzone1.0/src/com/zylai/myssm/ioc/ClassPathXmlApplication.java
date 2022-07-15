package com.zylai.myssm.ioc;

import com.zylai.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/13/11:34
 * @Description:
 */
public class ClassPathXmlApplication implements BeanFactory{

    private Map<String,Object> beanMap = new HashMap<>();
    private String path = "applicationContext.xml";

    public ClassPathXmlApplication(){
        this("applicationContext.xml");
    }
    public ClassPathXmlApplication(String path){
        if(StringUtil.isEmpty(path)){
            throw new RuntimeException("IOC容器的配置文件没有指定...");
        }
        try {
//        读取文件的信息
//        1.创建DocumentBuilderFactory对象
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
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
                    Class<?> BeanClass = Class.forName(className);
                    //创建bean对象实例
                    Object beanObj = BeanClass.newInstance();
//                    将id和类的实例对象放到map中
                    beanMap.put(beanId, beanObj);
                    //到目前为止，此处需要注意的是，bean和bean之间的关系还没有设置
                }
            }

            //5 组装bean之间的依赖关系
            for(int i=0;i<beanNodeList.getLength();i++){
                Node beanNode = beanNodeList.item(i);
                //判断是否为元素节点
                if(beanNode.getNodeType()==Node.ELEMENT_NODE){
                    //强转为element来获取id和class
                    Element beanElement = (Element)beanNode;
                    //获取当前bean的id
                    String beanId = beanElement.getAttribute("id");
                    //获取当前bean的子节点
                    NodeList beanChildNodeList = beanElement.getChildNodes();
                    for(int j=0;j<beanChildNodeList.getLength();j++){
                        Node beanChildNode = beanChildNodeList.item(j);
                        if(beanChildNode.getNodeType()==Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())){
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            //根据ref的值找到bean对象，然后给bean对象的属性赋值
                            //1)找到propertyRef对应的bean实例
                            Object refObj = beanMap.get(propertyRef);
                            //2) 将refObj设置到当前bean对应的实例的property属性上去
                            Object beanObj = beanMap.get(beanId);
                            Class<?> beanClazz = beanObj.getClass();
                            Field propertyField = beanClazz.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);
                        }
                    }
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
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Object getBean(String id) {

        return beanMap.get(id);
    }
}
