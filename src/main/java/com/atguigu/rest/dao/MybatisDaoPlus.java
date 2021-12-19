package com.atguigu.rest.dao;

import com.atguigu.rest.bean.Employee;

public interface MybatisDaoPlus {

    Employee selectById(Integer id);
}
