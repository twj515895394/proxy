package com.twj.javaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TxProxy implements InvocationHandler{
	private Object target;  
    /** 
     * 绑定委托对象并返回一个代理类  
     * @param target 
     * @return 
     */  
    public Object bind(Object target) {  
        this.target = target;  
        //取得代理对象  通过 Proxy 直接创建动态代理类实例
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)  
    }  

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		String methodName = method.getName();
		if(methodName.startsWith("add")||methodName.startsWith("update")){
			System.out.println("事务开启！");
		}
		//执行方法  
        result=method.invoke(target, args);
        if(methodName.startsWith("add")||methodName.startsWith("update")){
			System.out.println("事务提交并关闭！");
			System.out.println("--------------------------------------------");
		}
		return result;
	}
	
	public static void main(String[] args) {
		TxProxy proxy = new TxProxy();  
        BookFacade bookFacade = (BookFacade) proxy.bind(new BookFacadeImpl());  
        bookFacade.addBook();  
        bookFacade.selectBook();
	}
	
}
