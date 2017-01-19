package com.twj.javaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TxProxy implements InvocationHandler{
	private Object target;  
    /** 
     * ��ί�ж��󲢷���һ��������  
     * @param target 
     * @return 
     */  
    public Object bind(Object target) {  
        this.target = target;  
        //ȡ�ô������  ͨ�� Proxy ֱ�Ӵ�����̬������ʵ��
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this);   //Ҫ�󶨽ӿ�(����һ��ȱ�ݣ�cglib�ֲ�����һȱ��)  
    }  

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		String methodName = method.getName();
		if(methodName.startsWith("add")||methodName.startsWith("update")){
			System.out.println("��������");
		}
		//ִ�з���  
        result=method.invoke(target, args);
        if(methodName.startsWith("add")||methodName.startsWith("update")){
			System.out.println("�����ύ���رգ�");
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
