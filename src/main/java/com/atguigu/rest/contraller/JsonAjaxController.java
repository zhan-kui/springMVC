package com.atguigu.rest.contraller;

import com.atguigu.rest.bean.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonAjaxController {
    @ResponseBody
    @RequestMapping(value = "/ReRqBody",method = RequestMethod.POST)
    public User jsonResponseBody(RequestEntity<String> entity,User user){
        System.out.println("获取响应头"+ entity.getHeaders());
        System.out.println("获取响应体"+entity.getBody());
        System.out.println("获取响应路径"+entity.getUrl());


        return user;
    }
@ResponseBody
@RequestMapping(value = "/testAxios1" )
public User testAxios01(User user){
    System.out.println(user);
    return user;
}

    @ResponseBody
    @RequestMapping(value = "/error" )
    public void error(){
        int a = 10/0;


    }
}
