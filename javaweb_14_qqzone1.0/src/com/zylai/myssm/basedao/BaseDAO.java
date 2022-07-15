package com.zylai.myssm.basedao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/25/17:26
 * @Description:
 */
public abstract class BaseDAO<T> {

    //    老师是吧这几个放到这里的，我觉得可能会出现并发问题，不过目前不考虑这个
    protected Connection connection = null;
    protected PreparedStatement psmt = null;
    protected ResultSet rs = null;

    //    T的Class对象
    private Class entityClass;

    public BaseDAO() {
//        getClass获取Class对象，当前我们执行的是new FruitDAOImpl（），创建的是FruitDAOImpl实例
//        那么构造方法首先会调用父类（BaseDAO）的无参构造方法
//        因此此处的getClass方法执行，获取的是FruitDAOImpl的Class
//        所以使用getGenericSuperclass
//        getGenericSuperclass是获取父类的泛型，即获取BaseDAO的泛型
        Type genericSuperclass = getClass().getGenericSuperclass();
//         ParameterizedType:参数化类型
//        获取<T>（注意，泛型可以传递多个如<T,A,B>，所以返回值是一个数组）中的T的真是类型
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        Type actualType = actualTypeArguments[0];

        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO 构造器出问题");
        }
    }

    protected Connection getConnection(){
        return ConnUtil.getConn();
    }

    //    关闭资源
    protected  void close(Connection connection, PreparedStatement psmt, ResultSet rs){
        //先不让关闭连接
//        try {
//            if(rs!=null){
//                rs.close();
//            }
//            if(psmt!=null){
//                psmt.close();
//            }
//            if(connection!=null){
//                connection.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
    //    给预处理对象设置参数
    private void setParams(PreparedStatement psmt,Object... params) throws SQLException {
        if(params!=null && params.length>0){
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i+1,params[i]);
            }
        }
    }

    //   插入可以返回主键的值
    protected int insert(String sql,Object...params) {
        connection = getConnection();
        try {
//        创建prepareStatement对象时，传入第二个参数
            psmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
//            填充SQL中的参数
            setParams(psmt,params);
//            执行更新
            psmt.executeUpdate();
//            返回插入之后的主键值
            rs = psmt.getGeneratedKeys();
            if(rs.next()){
                return ((Long)rs.getLong(1)).intValue();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    //    执行更新，返回影响行数。
    protected int executeUpdate(String sql,Object... params) {
        try {
//            建立连接
            connection = getConnection();
//            定义psmt
            psmt = connection.prepareStatement(sql);
//            填充参数
            setParams(psmt,params);
//            执行，发挥影响行数
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("DAO executeUpdate 出错了");
        }
    }

    //    通过反射，给obj对象的property属性赋propertyValue值
    private void setValue(Object obj,String property,Object propertyValue) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = obj.getClass();
//        获取property这个字符串对应的属性名，比如“fid”去找obj中的fid属性
        Field field  = clazz.getDeclaredField(property);
        if(field!=null){
            //            强制访问
            field.setAccessible(true);
            //为obj对象设置filed属性的值
            field.set(obj,propertyValue);
        }
    }

    //    执行查询
    protected List<T> executeQuery(String sql,Object... params) {
        try {
            List<T> list = new ArrayList<>();
//            获取连接
            connection = getConnection();
//            设置预处理对象
            psmt = connection.prepareStatement(sql);
//            设置参数
            setParams(psmt,params);
//            执行查询
            rs= psmt.executeQuery();
//            处理结果集
//            难点在于如何处理T，我们不知道T是什么类型并且不知道T有多少个参数
//            通过rs可以获取结果集的元数据
//            元数据：描述结果集数据的数据，简单讲，就是这个结果接有哪些列，什么类型等等
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while(rs.next()){
                T entity = (T)entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
    //               获取列名，使用getColumnLabel，如果使用了别名，获取的就是别名
                    String columnName = metaData.getColumnLabel(i + 1);
    //                获取列对应的值
                    Object columnValue = rs.getObject(i + 1);
    //                    这里获取了列名，就需要根据列名把值填充给entity对象
                    setValue(entity,columnName,columnValue);
                }
                list.add(entity);

            }
            return list;
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            throw new DAOException("DAO executeQuery 出错了");
        }
    }

    //    通过查询返回一条记录
    protected T load(String sql,Object... params){
        try {
//            获取连接
            connection = getConnection();
//            设置预处理对象
            psmt = connection.prepareStatement(sql);
//            设置参数
            setParams(psmt,params);
//            执行查询
            rs= psmt.executeQuery();
//            处理结果集
//            难点在于如何处理T，我们不知道T是什么类型并且不知道T有多少个参数
//            通过rs可以获取结果集的元数据
//            元数据：描述结果集数据的数据，简单讲，就是这个结果接有哪些列，什么类型等等
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (rs.next()){
                T entity = (T)entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
    //               获取列名
                    String columnName = metaData.getColumnName(i + 1);
    //                获取列对应的值
                    Object columnValue = rs.getObject(i + 1);
    //                    这里获取了列名，就需要根据列名把值填充给entity对象
                    setValue(entity,columnName,columnValue);
                }
                return entity;
            }
            return null;
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            throw new DAOException("DAO load 出错了");
        }
    }

    //    执行复杂的查询，返回统计结果，这个方法暂时没有用到
    protected Object[] executeComplexQuery(String sql,Object... params)  {

        try {
//            获取连接
            connection = getConnection();
//            设置预处理对象
            psmt = connection.prepareStatement(sql);
//            设置参数
            setParams(psmt,params);
//            执行查询
            rs= psmt.executeQuery();
//            处理结果集
//            难点在于如何处理T，我们不知道T是什么类型并且不知道T有多少个参数
//            通过rs可以获取结果集的元数据
//            元数据：描述结果集数据的数据，简单讲，就是这个结果接有哪些列，什么类型等等
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (rs.next()){
                Object[] columnValueArr = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
    //                获取列对应的值
                    Object columnValue = rs.getObject(i + 1);
                    columnValueArr[i]=columnValue;
                }
                return columnValueArr;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("DAO executeComplexQuery 出错了");
        }
    }

}