package com.threadpool_�̳߳�1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Student {
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
	public Student(String string, int i) {
		this.name = string;
		this.score = i;
	}
	public static void main(String[] args) {
		 random = new Random();
		   List stuList = new ArrayList<Student>() {
		        {
		            for (int i = 0; i < 100; i++) {
		                add(new Student("student" + i, random.nextInt(50) + 50));
		            }
		        }
		    };

		// �г����ϳ���85�ֵ�ѧ�������������շ�����������û�����
		  /*  List<String> studentList = stuList.stream()
            .filter(x->((Student) x).getScore()>85)
            .sorted(Comparator.comparing(Student::getScore).reversed())
            .map(Student::getName)
            .collect(Collectors.toList());
    System.out.println(studentList);*/
	}
}
