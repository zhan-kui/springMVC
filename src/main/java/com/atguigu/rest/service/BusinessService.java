package com.atguigu.rest.service;


import com.atguigu.rest.bean.Employee;
import com.atguigu.rest.dao.MybatisDao;
import com.atguigu.rest.utils.MyBatisUtil;
import org.springframework.stereotype.Service;

import java.util.List;


public class  BusinessService {

    //根据页面传入的员工对象在数据库新增员工对象
   public static int insert(Employee employee){
       int insertCount = MyBatisUtil.executeSql(MybatisDao.class).insert(employee);

       return  insertCount;
   }

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

}
