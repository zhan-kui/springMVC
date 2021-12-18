package com.atguigu.rest.dao;

import com.atguigu.rest.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MybatisDao {

    //根据页面传入的员工对象在数据库新增员工对象
    int insert(Employee employee);

    //根据页面传入的员工对象在数据库修改员工对象
    int update(Employee employee);

    //根据主键员工ID删除一个员工对象
    int delete(Integer id);

    //返回所有员工对象的一个list集合
    List<Employee> getAll();

    //根据主键员工ID返回一个员工对象
    Employee get(Integer id);



}
