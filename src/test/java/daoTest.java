import com.atguigu.rest.bean.Employee;
import com.atguigu.rest.dao.MybatisDao;
import com.atguigu.rest.dao.MybatisDaoPlus;
import com.atguigu.rest.service.BusinessService;
import com.atguigu.rest.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class daoTest {



    @Test
    public void test3301() {

        List<Employee> employee = BusinessService.getAll();
        System.out.println(employee);

    }

    @Test
    public void test012() {
            Employee employee = new Employee("索隆222","dsdss@qq.com",19);

        int all = BusinessService.insert(employee);


        System.out.println("操作新增影响的行数" + all +"   通过返回数据库自增的主键获取的值：  "+employee.getId());


    }
    @Test
    public void test013() {

        int insert = MyBatisUtil.executeSql(MybatisDao.class).delete(1009);


        System.out.println(insert);


    }

    @Test
    public void testSelectById(){
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
    public void testSelectIf(){
        //1.获取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        //2.获取dao的代理
        MybatisDao dao = session.getMapper(MybatisDao.class);
        List<Employee>  employees = dao.getIf(new Employee("娜",null,null));
        System.out.println("student = " + employees);
        //3.关闭SqlSession对象
        session.close();
    }


    @Test
    public void getChoose(){
        //1.获取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        //2.获取dao的代理
        MybatisDao dao = session.getMapper(MybatisDao.class);
       List<Employee>  delete = dao.getChoose(new Employee(null,null,null));
        System.out.println("student = " + delete);
        //3.关闭SqlSession对象
        session.close();
    }

    @Test
    public void insertForeach(){
        //1.获取SqlSession
        SqlSession session = MyBatisUtil.getSqlSession();
        //2.获取dao的代理
        MybatisDao dao = session.getMapper(MybatisDao.class);
        ArrayList list = new ArrayList();
        list.add(new Employee("张三1","zhansan@qq.com",1));
        list.add(new Employee("张三22","zhansan@qq.com",1));
        list.add(new Employee("张三33","zhansan@qq.com",1));
        list.add(new Employee("张三44","zhansan@qq.com",1));
        int i = dao.insertForeach(list);
        System.out.println("新增行数 = " + i);
        //3.关闭SqlSession对象
        session.close();
    }


}
