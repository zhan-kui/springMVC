package com.atguigu.rest.service;


import com.atguigu.rest.bean.Employee;
import com.atguigu.rest.bean.User;
import com.atguigu.rest.dao.MybatisDao;
import com.atguigu.rest.utils.MyBatisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class  BusinessService {
    @Autowired
   static MybatisDao mybatisDao;


    //根据页面传入的员工对象在数据库新增员工对象
   public static int insert(Employee employee){

       int insertCount = MyBatisUtil.executeSql(MybatisDao.class).insert(employee);
//       int insertCount = mybatisDao.insert(employee);
       return  insertCount;
   }

    //根据页面传入的员工对象在数据库新增员工对象，且返回新增这个员工的数据库自动增长主键的值
//    public static Employee1 insertGetKey (Employee employee){
//        Employee1 employee1 =  MyBatisUtil.executeSql(MybatisDao.class).insertGetKey(employee);
//
//        return  employee1;
//    }

    //根据页面传入的员工对象在数据库修改员工对象
    public static int update(Employee employee){

        return MyBatisUtil.executeSql(MybatisDao.class).update(employee);

    }

    //根据主键员工ID删除一个员工对象
    public static int delete(Integer id){

        return MyBatisUtil.executeSql(MybatisDao.class).delete(id);
    }

    //返回所有员工对象的一个list集合
    public static List<Employee> getAll(){


        return MyBatisUtil.executeSql(MybatisDao.class).getAll();

    }

    //根据主键员工ID返回一个员工对象
    public static Employee get(Integer id){

        return MyBatisUtil.executeSql(MybatisDao.class).get(id);

    }

    //根据员工任意属性查询，返回一个员工对象。。。动态条件sql，用了like模糊查询
    public static List< Employee> getIf(Employee emp){

        return MyBatisUtil.executeSql(MybatisDao.class).getIf(emp);

    }
    //根据员工任意属性查询，返回一个员工对象。。。动态条件sql，没有用like模糊查询
    public static  Employee getIfNoLike(Employee emp){

        return MyBatisUtil.executeSql(MybatisDao.class).getIfNoLike(emp);

    }



}
