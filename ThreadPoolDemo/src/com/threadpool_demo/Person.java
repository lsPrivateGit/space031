package com.threadpool_demo;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @Author: LinSong
 * @Date: 2018/12/29 15:25
 */
public class Person {

    private String name;
    private String sex;


    public static void main(String[] args) {
        Person person1 = new Person();
        person1.setName("name1");
        person1.setSex("sex1");
        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(person1);
        } catch (Exception e) {

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}