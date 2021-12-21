package com.atguigu.rest.contraller;

import com.atguigu.rest.bean.Employee;
import com.atguigu.rest.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class EmployeeController {


    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    public String getAllEmployee(Model model){  //查询所有的员工信息
        Collection<Employee> employeeList = BusinessService.getAll();
        model.addAttribute("employeeList",employeeList);
        return "employee_list";
    }
    @RequestMapping(value = "/employee/{id}",method = RequestMethod.DELETE)
    public  String deleteEmployee (@PathVariable("id") Integer id){  //删除员工

        BusinessService.delete(id);
        return "redirect:/employee"; //这个地方如果写错了会报500错误，template: 解析模板错误，如果加/必须用转发或者重定向
        // "ServletContext resource [/WEB-INF/templates/redireck:/employee.html]"

    }//下面这里employee{id} 中间少了个/ 一直找不到这个方法，导致去匹配前面的DELETE的方法一直提示请求方式错误，要仔细啊
  @RequestMapping(value = "/employee/{id}" ,method = RequestMethod.GET)
    public String getEmployeeById (@PathVariable("id") Integer id ,Model model){//通过员工编号获取员工信息

      System.out.println(id +"===========================================================");
    Employee employee = BusinessService.get(id);
    model.addAttribute("employee",employee);
    return "employee_update";
    }

    @RequestMapping(value = "/employee",method = RequestMethod.PUT)
public String updateEmployee(Employee employee){ //修改员工信息
        BusinessService.update(employee);
        return "redirect:/employee";
}
@RequestMapping(value = "employee" ,method = RequestMethod.POST)
public String addEmployee( Employee employee){  //添加员工信息
    BusinessService.insert(employee);
        return "redirect:/employee";
}

}
