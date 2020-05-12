package com.ysq.testdemo.proxy;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/12/9
 * <p>
 * 包 名：com.ysq.testdemo.proxy
 * <p>
 * 类 名：ProxyClass
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class ProxyClass implements ProxyInterface{


    private  TargetClass mTargetClass;

    public ProxyClass(TargetClass  pTargetClass) {
        mTargetClass = pTargetClass;
    }

    @Override
    public void testProxy() {
        Log.e("CHRIS", "testProxy: 开始静态代理");
        if(mTargetClass != null)
            mTargetClass.testProxy();
        Log.e("CHRIS", "testProxy: 结束静态代理");
    }



    public static Object loadProxy(final  Object target) throws Exception {
        //通过接口Class对象创建代理Class对象
        Class<?> proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());

        //拿到代理Class对象的有参构造方法
        Constructor<?> constructors = proxyClass.getConstructor(InvocationHandler.class);
        //反射创建代理实例
        Object proxy = constructors.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Log.e("CHRIS", "testProxy: 开始动态代理");
                //执行目标类的方法
                Object result = method.invoke(target, args);
                Log.e("CHRIS", "testProxy: 结束动态代理");

                return result;
            }
        });

        Log.e("CHRIS", "proxyClass: " + (proxy instanceof Proxy));

        Log.e("CHRIS", "proxyClass: " + (proxyClass.getName()));

        Log.e("CHRIS", "proxyClass: " + (proxyClass.getSuperclass().getName()));

        Log.e("CHRIS", "proxyClass: " + (proxyClass.getSuperclass().getSimpleName()));

        Log.e("CHRIS", "proxyClass: " +(proxyClass.getInterfaces()[0].getSimpleName()));

        return proxy;
    }

}
