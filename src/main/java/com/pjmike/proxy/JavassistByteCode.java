package com.pjmike.proxy;

import javassist.*;

/**
 * javassist字节码
 *
 * @author pjmike
 * @create 2018-08-07 0:07
 */
public class JavassistByteCode {
    public static void main(String[] args) throws IllegalAccessException, CannotCompileException, InstantiationException, NotFoundException {
        ByteCodeAPI byteCodeApi = createJavassistBycodeDynamicProxy();
        System.out.println(byteCodeApi.sayHello());
    }
    public static ByteCodeAPI createJavassistBycodeDynamicProxy() throws CannotCompileException, IllegalAccessException, InstantiationException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass(ByteCodeAPI.class.getName()+"demo");
        cc.addInterface(pool.get(ByteCodeAPI.class.getName()));
        //创建属性
        CtField field = CtField.make("private String a;", cc);
        cc.addField(field);
        //创建方法
        CtMethod method = CtMethod.make("public String sayHello() {return \"hello\";}", cc);
        cc.addMethod(method);
        //添加构造器
        cc.addConstructor(CtNewConstructor.defaultConstructor(cc));
        Class<?> pc = cc.toClass();
        ByteCodeAPI byteCodeApi = (ByteCodeAPI) pc.newInstance();
        return byteCodeApi;
    }
}
interface ByteCodeAPI {
    public String sayHello();
}