import com.atguigu.rest.bean.Employee;
import com.atguigu.rest.dao.MybatisDao;
import com.atguigu.rest.service.BusinessService;
import com.atguigu.rest.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;


public class daoTest {



    @Test
    public void test3301() {

        List<Employee> employee = BusinessService.getAll();
        System.out.println(employee);

    }

    @Test
    public void test012() {
            Employee employee = new Employee(1,"索隆222","dsdss@qq.com",19);

        List<Employee> all = BusinessService.getAll();


        System.out.println(all);


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
        MybatisDao dao = session.getMapper(MybatisDao.class);
        int delete = dao.delete(1008);
        System.out.println("student = " + delete);
        //3.关闭SqlSession对象
        session.close();
    }





}
