package com.twj.cglib;

import java.lang.reflect.Method;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class BookFacadeCglib implements MethodInterceptor{
	 private Object target;  
	  
    /** 
     * 创建代理对象 
     *  
     * @param target 
     * @return 
     */  
    public Object getInstance(Object target) {  
        this.target = target;  
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(this.target.getClass());  
        // 回调方法  
        enhancer.setCallback(this);  
        // 创建代理对象  
        return enhancer.create();  
    }  
	    
	@Override
	public Object intercept(Object object, Method method, Object[] objects,
			MethodProxy methodProxy) throws Throwable {
		Object result = null;
		String methodName = method.getName();
		if(methodName.startsWith("add")||methodName.startsWith("update")){
			System.out.println("事务开启！");
		}
		//执行方法  
        result=method.invoke(target, objects);
        if(methodName.startsWith("add")||methodName.startsWith("update")){
			System.out.println("事务提交并关闭！");
			System.out.println("--------------------------------------------");
		}
		return result;
	}
	
	public static void main(String[] args) {
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookFacade bookFacade = (BookFacade) cglib.getInstance(new BookFacade());  
        bookFacade.addBook();  
        bookFacade.selectBook();
	}

}
