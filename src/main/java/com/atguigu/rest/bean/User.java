package com.atguigu.rest.bean;

import org.springframework.stereotype.Component;

@Component
public class User implements UserInterface {
String name;
int age;
@Override
public String test() {

    System.out.println("调用了user的方法了！！！！");
    return "I love java";
}

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
