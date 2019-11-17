package com.pjmike.proxy;

        import net.sf.cglib.proxy.Enhancer;
        import net.sf.cglib.proxy.MethodInterceptor;
        import net.sf.cglib.proxy.MethodProxy;

        import java.lang.reflect.Method;

/**
 * CGLIB动态代理实现
 *
 * @author pjmike
 * @create 2018-08-06 16:55
 */
public class CglibProxy {
    public static void main(String[] args) {
        //
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback(new MyMethodInterceptor());
        //通过enhancer.create()方法获取代理对象
        //对代理对象所有非final的方法调用都会转发给MethodInterceptor.intercept方法,
        //作用跟JDK动态代理的InvocationHandler类似
        PersonService personService = (PersonService) enhancer.create();
        System.out.println(personService.sayHello("pjmike"));
    }
}

class PersonService {
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}

class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println(">>>> before intercept");
        Object o = methodProxy.invokeSuper(obj, args);
        System.out.println(">>>> after intercept");
        return o;
    }
}