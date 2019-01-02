package com.java8.stream;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class Student2 {
	private String name;
    private Integer score;
    
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	static Random random ;
	public Student2(String string, int i) {
		this.name = string;
		this.score = i;
	}
	
	
	public static void main(String[] args) {
		 random = new Random();
		   List<Student2> stuList = new ArrayList<Student2>() {
		        {
		            for (int i = 0; i < 100; i++) {
		                add(new Student2("student" + i, random.nextInt(50) + 50));
		            }
		        }
		    };
		    
		    for(Student2 student : stuList){
		    	if(student.getScore()>85){
		    		System.out.print(student.getName()+"--"+student.getScore());
		    	}
		    }
		    
		    System.out.println();
		   // 列出班上超过85分的学生姓名，并按照分数降序输出用户名字
		    List studentList = stuList.stream()
           .filter(a -> a.getScore()>85)
           .sorted(Comparator.comparing(Student2::getScore))
           .map(Student2::getName)
           .collect(Collectors.toList());
           System.out.println(studentList);
           
           
	}
}