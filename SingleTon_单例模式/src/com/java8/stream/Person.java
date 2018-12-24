package com.java8.stream;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public static List<Person> getList() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Ram", 23));
        list.add(new Person("Shyam", 20));
        list.add(new Person("Shiv", 25));
        list.add(new Person("Mahesh", 30));
        return list;
    }
} 


