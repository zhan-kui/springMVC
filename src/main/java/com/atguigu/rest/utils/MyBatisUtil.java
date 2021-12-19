package com.atguigu.rest.utils;


import com.atguigu.rest.bean.Employee;
import com.atguigu.rest.dao.MybatisDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;



/**
 * 工具类：创建SqlSession对象
 */
public class MyBatisUtil {


   /* private static SqlSessionFactory sqlSessionFactory = null; //放在外面定义这个对象是全局的，如果只放在try里面就是局部变量

    static {
        String config = "myba.xml"; //获取mybatis的配置文件
        try {
            InputStream inputStream = Resources.getResourceAsStream("ms.xml"); //通过输入流的方式加载mybatis配置文件给build使用

            //通过SqlSessionFactoryBuilder().build加载获取mybatis的配置文件后生成sqlSessionFactory的工程类对象，然后去造sqlSession
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建方法，获取SqlSession对象

    public static SqlSession getSqlSession() {
        // 通过上面的sqlSessionFactory工厂类对象调用openSession()生成SqlSession对象，用来执行mapper文件中定义的sql语句
        //注意：这里openSession()方法里面有个boolean可以进行设定，默认是false，也就是在增删改时不默认提交事务，写完后要在后面commit提交，如果为true每次执行都会自动提交
        SqlSession sqlSession =null;//如果不在外面定义，return就取不到if里面的值，因为在里面定义的都是局部变量
        if (sqlSessionFactory != null)
        {
            sqlSession = sqlSessionFactory.openSession();
        }
        return sqlSession;
    }*/

    private static SqlSessionFactory factory  = null;

    static {

        String config="mybatis.xml";
        try {
            InputStream inputStream  =Resources.getResourceAsStream(config);
            factory  = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建方法，获取SqlSession对象
    public static SqlSession getSqlSession(){
        SqlSession session = null;
        if( factory != null ){
            session =factory.openSession(true);// openSession(true);
//            MybatisDao mapper = session.getMapper(MybatisDao.class);

        }
        return session;
    }

//自己写的工具类，通过反射和泛型的方式通过传入一个Dao的实现类（这个实现类是不用自己写，框架自动做了，前提是在xml里面配置的id方法名和namespace接口名一致，注意：在Mybatis配置文件配置mapper文件路径是用真实路径带斜杆，在mapper文件配置接口和方法用小数点.用斜杆报错）
// 的Class,
// 根据传入的的类型调用getMapper返回这个Dao的对象，直接调用Dao下面的方法
    public static  <T> T executeSql (Class<T> clazz ){
        SqlSession session = MyBatisUtil.getSqlSession();
        T mapper = (T) session.getMapper(clazz);

        return mapper;

    }


}
