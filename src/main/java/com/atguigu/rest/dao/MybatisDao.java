package com.atguigu.rest.dao;

import com.atguigu.rest.bean.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MybatisDao {

    //根据页面传入的员工对象在数据库新增员工对象
    int insert(Employee employee);
    //根据页面传入的员工对象在数据库批量插入新增员工对象，因为是foreach 记得把参数名称定义为xml里面的一致，不然会提示找不到集合
    int insertForeach(@Param("listEmployee") ArrayList listEmployee);

//    //根据页面传入的员工对象在数据库新增员工对象，且返回新增这个员工的数据库自动增长主键的值
//    Employee1 insertGetKey(Employee employee);

    //根据页面传入的员工对象在数据库修改员工对象
    int update(Employee employee);

    //根据主键员工ID删除一个员工对象
    int delete(Integer id);

    //根据主键员工ID删除一个员工对象
    int deleteForeach(List list);

    //返回所有员工对象的一个list集合
    List<Employee> getAll();
    List<Employee> getAll01();


    //根据主键员工ID返回一个员工对象
    Employee get(Integer id);

    //根据员工任意属性查询,且使用了like，返回一个员工对象。。。动态条件sql
    List< Employee> getIf(Employee employee);

    //根据员工任意属性查询，返回一个员工对象。。。动态条件sql
   Employee getIfNoLike(Employee employee);

    //根据员工任意属性查询，返回一个员工对象。。。动态条件sql
    List< Employee> getChoose(Employee employee);



}
