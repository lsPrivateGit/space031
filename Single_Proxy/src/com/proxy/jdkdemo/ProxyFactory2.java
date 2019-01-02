package com.proxy.jdkdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory2 implements InvocationHandler {
   private Object target;//目标对象
    /**
     * 通过反射来实例化目标对象
     * @param object
     * @return
     */
	public Object build(Object object){
		this.target = object;
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), 
				this.target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result;
		result = method.invoke(target, args);
		return result;
	}

}
