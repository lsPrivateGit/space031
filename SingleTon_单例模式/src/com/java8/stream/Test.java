package com.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
		  
   /*     List<String> list = Arrays.asList("A","B","A","D");
        String result=  list.stream().collect(Collectors.joining());
        
        Student s1 = new Student();
        s1.setCity("chengdu");
        Student s2 = new Student();
        s2.setCity("shenzhen");
        Student s3 = new Student();
        s3.setCity("chengdu");
        List<Student> studentList = Arrays.asList(s1,s2,s3);

        long count = studentList.stream().filter((student -> student.getCity().equals("chengdu"))).count();
        
        System.out.println(count);
        
        String b = "";
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API",
        		 "Date and Time API");
		features.forEach(n ->{
			if(n.equals("Lambdas")){
				System.out.println(n);
			}
		});*/
		 List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

		  System.out.println("Languages which starts with J :");
		  filter(languages, str -> str.startsWith("J"));

		  System.out.println("Languages which ends with a ");
		  filter(languages, str -> str.endsWith("a"));

		  System.out.println("Print all languages :");
		  filter(languages, str -> true);

		   System.out.println("Print no language : ");
		   filter(languages, str -> false);

		   System.out.println("Print language whose length greater than 4:");
		   filter(languages, str -> str.length() > 4);
        		
        		
	}
	/*public static void filter(List<String> names, Predicate condition) {
	    for(String name: names){
	       if(condition.test(name)) {
	          System.out.println(name + " ");
	       }
	    }
	  }*/
	
	public static void filter(List<String> names, Predicate<String> condition) {
	    names.stream().filter(name -> (condition.test(name)))
	        .forEach((name) -> {System.out.println(name + " ");
	    });
	 }
}
