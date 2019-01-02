package com.threadpool_demo;

public class Java8Tester {
   public static void main(String args[]){
   
      /*List<String> names1 = new ArrayList<String>();
      names1.add("Google ");
      names1.add("Runoob ");
      names1.add("Taobao ");
      names1.add("Baidu ");
      names1.add("Sina ");
        
      List<String> names2 = new ArrayList<String>();
      names2.add("Google ");
      names2.add("Runoob ");
      names2.add("Taobao ");
      names2.add("Baidu ");
      names2.add("Sina ");
        
      Java8Tester tester = new Java8Tester();
      System.out.println("ʹ�� Java 7 �﷨: ");
        
      tester.sortUsingJava7(names1);
      System.out.println(names1);
      System.out.println("ʹ�� Java 8 �﷨: ");
        
      tester.sortUsingJava8(names2);
      System.out.println(names2);
   }
   
   // ʹ�� java 7 ����
   private void sortUsingJava7(List<String> names){   
      Collections.sort(names, new Comparator<String>() {
         @Override
         public int compare(String s1, String s2) {
            return s1.compareTo(s2);
         }
      });
   }
   
   // ʹ�� java 8 ����
   private void sortUsingJava8(List<String> names){
      Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
   }*/
	   
	   Java8Tester tester = new Java8Tester();
       
	      // ��������
	      MathOperation addition = (int a, int b) -> a + b;
	        
	      // ������������
	      MathOperation subtraction = (a, b) -> a - b;
	        
	      // �������еķ������
	      MathOperation multiplication = (int a, int b) -> { return a * b; };
	        
	      // û�д����ż��������
	      MathOperation division = (int a, int b) -> a / b;
	        
	      System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
	      System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
	      System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
	      System.out.println("10 / 5 = " + tester.operate(10, 5, division));
	        
	      // ��������
	      GreetingService greetService1 = message ->
	      System.out.println("Hello " + message);
	        
	      // ������
	      GreetingService greetService2 = (message) ->
	      System.out.println("Hello " + message);
	        
	      greetService1.sayMessage("Runoob");
	      greetService2.sayMessage("Google");
	   }
	    
	   interface MathOperation {
	      int operation(int a, int b);
	   }
	    
	   interface GreetingService {
	      void sayMessage(String message);
	   }
	    
	   private int operate(int a, int b, MathOperation mathOperation){
	      return mathOperation.operation(a, b);
	   }
	   
	   
}