package com.atguigu.rest.contraller;

import com.atguigu.rest.bean.Employee;
import com.atguigu.rest.bean.Msg;
import com.atguigu.rest.bean.User;
import com.atguigu.rest.bean.UserInterface;
import com.atguigu.rest.dao.MybatisDao;
import com.atguigu.rest.service.BusinessService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class EmployeeController {

    @Autowired
    MybatisDao mybatisDao;


    @RequestMapping(value = "/employeeCrud", method = RequestMethod.GET)
//改版后的查询所有及分页方法
    public String getAllEmployee01(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Employee employee, Model model) {  //查询所有的员工信息


        PageHelper.startPage(pn, 10);
        List<Employee> employeeList = mybatisDao.getIf(employee);
        PageInfo page = new PageInfo(employeeList, 10);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("pageInfo", page);
        return "crud";

    }



    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getAllEmployee(Model model) {  //查询所有的员工信息
        Collection<Employee> employeeList = mybatisDao.getAll();
        model.addAttribute("employeeList", employeeList);
        return "employee_list";
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Integer id) {  //删除员工

        BusinessService.delete(id);
        return "redirect:/employee"; //这个地方如果写错了会报500错误，template: 解析模板错误，如果加/必须用转发或者重定向
        // "ServletContext resource [/WEB-INF/templates/redireck:/employee.html]"


    }

    //下面这里employee{id} 中间少了个/ 一直找不到这个方法，导致去匹配前面的DELETE的方法一直提示请求方式错误，要仔细啊
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable("id") Integer id, Model model) {//通过员工编号获取员工信息

        System.out.println(id + "===========================================================");
        Employee employee = BusinessService.get(id);
        model.addAttribute("employee", employee);
        return "employee_update";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public String updateEmployee(Employee employee) { //修改员工信息
        BusinessService.update(employee);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String addEmployee(Employee employee) {  //添加员工信息
        BusinessService.insert(employee);
        return "redirect:/employee";
    }

    @ResponseBody
    @RequestMapping(value = "/employeeSelect")
    public Msg employeeSelect(Employee employee) {
        //先判断用户名是否是合法的表达式;
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";


//        if(!Msg.regexCheckIdentityCard(employee.getLastName())){  这个是验证身份证号码，亲测可用
        if (!employee.getLastName().matches(regx)) {
            return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }

        Employee emp = mybatisDao.getIfNoLike(employee);

        if (emp != null) {
            return Msg.fail().add("va_msg", "用户名已存在");
        } else
            return Msg.success();
    }

    ////改版后的删除方法，单个和批量删除二合一
    @ResponseBody
    @RequestMapping(value = "/employeeBatchDelete/{id}", method = RequestMethod.DELETE)
    public Msg employeeBatchDelete(@PathVariable("id") String id) {
        if (id.contains("_")) {
            String[] splitId = id.split("_");
            ArrayList list = new ArrayList();
            for (String string : splitId) {
                list.add(Integer.parseInt(string));
            }
            int i = mybatisDao.deleteForeach(list);
            return Msg.success().add("va_msg", "已成功删除" + i + "行记录");
        } else {
            int i = Integer.parseInt(id);

            int deleteid = mybatisDao.delete(i);
            return Msg.success().add("va_msg", "已成功删除" + deleteid + "行记录");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/employeeNew", method = RequestMethod.POST)
    public Msg updateNew(Employee employee) { //修改员工信息
        BusinessService.update(employee);
        return Msg.success();
    }


}
