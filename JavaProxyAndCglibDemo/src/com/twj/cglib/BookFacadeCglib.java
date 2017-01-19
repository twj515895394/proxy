package com.twj.cglib;

import java.lang.reflect.Method;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class BookFacadeCglib implements MethodInterceptor{
	 private Object target;  
	  
    /** 
     * ����������� 
     *  
     * @param target 
     * @return 
     */  
    public Object getInstance(Object target) {  
        this.target = target;  
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(this.target.getClass());  
        // �ص�����  
        enhancer.setCallback(this);  
        // �����������  
        return enhancer.create();  
    }  
	    
	@Override
	public Object intercept(Object object, Method method, Object[] objects,
			MethodProxy methodProxy) throws Throwable {
		Object result = null;
		String methodName = method.getName();
		if(methodName.startsWith("add")||methodName.startsWith("update")){
			System.out.println("��������");
		}
		//ִ�з���  
        result=method.invoke(target, objects);
        if(methodName.startsWith("add")||methodName.startsWith("update")){
			System.out.println("�����ύ���رգ�");
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
