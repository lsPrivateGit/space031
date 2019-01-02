package com.java8.stream;


import org.junit.Test;

/**
 * 函数接口测试
 * 
 */
public class FunctionInterfaceTest {

    @Test
    public void testLambda() {
       func(new FunctionInterface() {
		@Override
		public void test() {
			System.out.println("无参数");
			
		}
	});
        //使用Lambda表达式代替上面的匿名内部类
        func(() -> System.out.println("Hello World"));
    }


	private void func(FunctionInterface functionInterface) {
        functionInterface.test();
    }
	
	@Test
    public void testLambda2() {
       func2(new FunctionInterface2() {
		@Override
		public void test(int par) {
			System.out.println("有参数 par="+par);
			
		}
	});
        //使用Lambda表达式代替上面的匿名内部类
        func2((x) -> System.out.println("helloworld 有参数 par="+x));
    }


	private void func2(FunctionInterface2 functionInterface2) {
		 int x = 1;
		 functionInterface2.test(x);
    }
}