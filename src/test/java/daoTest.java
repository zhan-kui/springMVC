import com.atguigu.rest.bean.Employee;
import com.atguigu.rest.bean.UserInterface;
import com.atguigu.rest.dao.MybatisDao;
import com.atguigu.rest.dao.MybatisDaoPlus;
import com.atguigu.rest.service.BusinessService;
import com.atguigu.rest.utils.MyBatisUtil;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/*用于spring项目的单元测试，先导入spring-test包，可以自动注入我们需要的组件，也就是没在springMVC的web工程也能自动装配IOC容器。
        如果没有这个单元测试，要通过spring IOC的容器自动装配必须要 读取spring的xml文件生成ApplicationContext对象
        在通过applicationContext.getBean("user", User.class) 吧bean的ID名称和 类的Class放进去才能用IOC容器生成对象且自动装配;*/
@RunWith(SpringJUnit4ClassRunner.class)  //选择要使用哪个spring测试类
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", "classpath:springMVC/springMVC.xml"})
//指定spring的配置文件，也就是bean的xml文件,然后直接使用@Autowiredclasspath:

@WebAppConfiguration  //这个注解可以在测试类里面模拟MVC发起的请求响应，省去了用页面去测试MVC收到请求后的响应结果
public class daoTest {
    @Autowired
    MybatisDao mybatisDao;
    MockMvc mockMvc;  //虚拟的MVC请求，用来做测试
    @Autowired
    WebApplicationContext context;
    @Autowired
    SqlSession session;  //通过xml配置创建的session进行批量操作

    @Before
    public void initMokcMvc() {  //初始化mockMvc，需要加载MVC自身的容器  WebApplicationContext
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }
    @Test
    public void pageTest() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/employeeCrud").param("pn", "1")).andReturn();


        MockHttpServletRequest request = result.getRequest();
        PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码： "+pi.getPageNum());
        System.out.println("总页码： "+pi.getPages());
        System.out.println("总记录数： "+pi.getTotal());
        System.out.println("在页面需要连续显示的号码： ");
        int[] navigatepageNums = pi.getNavigatepageNums();
        for (int i : navigatepageNums) {
            System.out.println(""+ i);
        }
        List<Employee> employeeList = pi.getList();
        System.out.println(employeeList);


    }

    @Test
    public void BatchInsert() {  //测试批量插入，使用batch刷新
//
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//    SqlSessionTemplate session = applicationContext.getBean("sqlSession", SqlSessionTemplate.class);
        MybatisDao mapper = session.getMapper(MybatisDao.class);
        ArrayList list = new ArrayList();
        list.add(new Employee("张三1", "zhansan@qq.com", 1));
        list.add(new Employee("张三22", "zhansan@qq.com", 1));
        list.add(new Employee("张三33", "zhansan@qq.com", 1));
        list.add(new Employee("张三44", "zhansan@qq.com", 1));
        int i = mapper.insertForeach(list);
        System.out.println("新增行数 = " + i);

//        由于加入了spring的注解就可以在spring框架上面直接使用IOC容器了，不需要通过applicationContext来加载bean文件去获取，但只能调用方法。

  /*      user.test();
        List<Employee> employee = BusinessService.getAll();
        System.out.println(employee);
*/
    }

    @Test
    public void insertOrDeleteTest() {
        //新增后，通过设置mapper的属性，获取返回数据库自增的主键获取的值
      /*  Employee employee = new Employee("索隆222", "dsdss@qq.com", 19);
        int insert = BusinessService.insert(employee);
        System.out.println("操作新增影响的行数" + insert + "   通过返回数据库自增的主键获取的值：  " + employee.getId());*/


        int delete = MyBatisUtil.executeSql(MybatisDao.class).delete(1009);
        System.out.println("删除影响的行数为：  " + delete);


    }

    @Test
    public void testSelectById() {
        //1.获取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        //2.获取dao的代理
        MybatisDaoPlus dao = session.getMapper(MybatisDaoPlus.class);
        Employee delete = dao.selectById(1);
        System.out.println("student = " + delete);
        //3.关闭SqlSession对象
        session.close();
    }

    @Test
    public void testSelectIf() {
     /*   //1.获取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        //2.获取dao的代理
        MybatisDao dao = session.getMapper(MybatisDao.class);
        List<Employee> employees = dao.getIf(new Employee("娜", null, null));
        System.out.println("student = " + employees);
        //3.关闭SqlSession对象
        session.close();*/

        List<Employee> a = BusinessService.getIf(new Employee("美", null, null));
        Employee b= BusinessService.getIfNoLike(new Employee("na",null,null));
        System.out.println("=========================" + a);
        System.out.println("=========================" + b);

    }


    @Test
    public void getChoose() {
        //1.获取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();

        //2.获取dao的代理
        MybatisDao dao = session.getMapper(MybatisDao.class);
        List<Employee> delete = dao.getChoose(new Employee(null, null, null));
        System.out.println("student = " + delete);
        //3.关闭SqlSession对象
        session.close();
    }

    @Test
    public void insertForeach() {
        //1.获取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        //2.获取dao的代理
        MybatisDao dao = session.getMapper(MybatisDao.class);
        ArrayList list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            String uid = UUID.randomUUID().toString().substring(0,3)+i;
            uid= "娜美"+ uid;
            list.add(new Employee(uid, "zhansan@qq.com", 1));

        }

        int i = dao.insertForeach(list);
        System.out.println("新增行数 = " + i);
        //3.关闭SqlSession对象
        session.close();


    }

    @Test
    public void tsess() {
        //1.获取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        System.out.println(session);
        //2.获取dao的代理
//        MybatisDao dao = session.getMapper(MybatisDao.class);
//        ArrayList list = new ArrayList();
//        list.add(new Employee("张三1", "zhansan@qq.com", 1));
//        list.add(new Employee("张三22", "zhansan@qq.com", 1));
//        list.add(new Employee("张三33", "zhansan@qq.com", 1));
//        list.add(new Employee("张三44", "zhansan@qq.com", 1));
//        int i = dao.insertForeach(list);
//        System.out.println("新增行数 = " + i);
//        //3.关闭SqlSession对象
//        session.close();


    }
    @Test
    public void deleteForeach1(){
      /*  List list = new ArrayList(Arrays.asList(5070,5071,5072,5073,5074));
        mybatisDao.deleteForeach(list);*/

        List<Employee> anIf = mybatisDao.getIf(new Employee("3333", null, null));
        System.out.println(" ============================="+anIf);
    }


}


