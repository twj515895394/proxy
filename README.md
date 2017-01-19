# proxy
java 动态代理demo

观察代码可以发现每一个代理类只能为一个接口服务，这样一来程序开发中必然会产生过多的代理，而且，所有的代理操作除了调用的方法不一样之外，其他的操作都一样，则此时肯定是重复代码。解决这一问题最好的做法是可以通过一个代理类完成全部的代理功能，那么此时就必须使用动态代理完成。 
再来看一下动态代理： 
JDK动态代理中包含一个类和一个接口： 
InvocationHandler接口： 
public interface InvocationHandler { 
public Object invoke(Object proxy,Method method,Object[] args) throws Throwable; 
} 
参数说明： 
Object proxy：指被代理的对象。 
Method method：要调用的方法 
Object[] args：方法调用时所需要的参数 

可以将InvocationHandler接口的子类想象成一个代理的最终操作类，替换掉ProxySubject。 

Proxy类： 
Proxy类是专门完成代理的操作类，可以通过此类为一个或多个接口动态地生成实现类，此类提供了如下的操作方法： 
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, 
InvocationHandler h) 
                               throws IllegalArgumentException 
参数说明： 
ClassLoader loader：类加载器 
Class<?>[] interfaces：得到全部的接口 
InvocationHandler h：得到InvocationHandler接口的子类实例 

Ps:类加载器 
在Proxy类中的newProxyInstance（）方法中需要一个ClassLoader类的实例，ClassLoader实际上对应的是类加载器，在Java中主要有一下三种类加载器; 
Booststrap ClassLoader：此加载器采用C++编写，一般开发中是看不到的； 
Extendsion ClassLoader：用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类; 
AppClassLoader：(默认)加载classpath指定的类，是最常使用的是一种加载器。 

动态代理 
与静态代理类对照的是动态代理类，动态代理类的字节码在程序运行时由Java反射机制动态生成，无需程序员手工编写它的源代码。动态代理类不仅简化了编程工作，而且提高了软件系统的可扩展性，因为Java 反射机制可以生成任意类型的动态代理类。java.lang.reflect 包中的Proxy类和InvocationHandler 接口提供了生成动态代理类的能力。 


但是，JDK的动态代理依靠接口实现，如果有些类并没有实现接口，则不能使用JDK代理，这就要使用cglib动态代理了。 

Cglib动态代理 
JDK的动态代理机制只能代理实现了接口的类，而不能实现接口的类就不能实现JDK的动态代理，cglib是针对类来实现代理的，他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。 
