package com.java8.stream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class JoiningExampleWithListOfObject {
    public static void main(String[] args) {
        List<Person> list = Person.getList();
        System.out.println("--Join person name--");
        String result=  list.stream().map(p -> p.getName()).collect(Collectors.joining());
        System.out.println(result);
        result=  list.stream().map(p -> p.getName()).collect(Collectors.joining("|"));
        System.out.println(result);
        result=  list.stream().map(p -> p.getName()).collect(Collectors.joining("-","[","]"));
        System.out.println(result);

        System.out.println("--Join person age--");
        result=  list.stream().map(p -> String.valueOf(p.getAge())).collect(Collectors.joining());
        System.out.println(result);
        result=  list.stream().map(p -> String.valueOf(p.getAge())).collect(Collectors.joining("|"));
        System.out.println(result);
        result=  list.stream().map(p -> String.valueOf(p.getAge())).collect(Collectors.joining("-","[","]"));
        System.out.println(result);       

        System.out.println("--Join person name-age--");
        result=  list.stream().map(p -> p.getName()+"-" + p.getAge()).collect(Collectors.joining("|"));
        System.out.println(result);
        result=  list.stream().map(p -> p.getName()+"-" + p.getAge()).collect(Collectors.joining("|","[","]"));
        System.out.println(result);  
        
        String  messages = "#name#";
        String[] mess = new String[]{messages};
        Map<String,String> map = new HashMap<>();
        map.put("name","张三");
        map.forEach((key,value)->{
            if(mess[0].contains(key)){
                mess[0]=mess[0].replaceAll("#"+key+"#",value);
            }
        });
        System.out.println(mess[0]);
        
        
    }       
} 
