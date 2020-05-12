package com.ysq.testdemo.reflect;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/12/5
 * <p>
 * 包 名：com.ysq.testdemo.test
 * <p>
 * 类 名：TestReflection
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class TestReflection {

    private static final String TAG = "Reflection";
    public void testReflection() {
        Class<?> c = HashMap.class;
        //获取类名
        Log.d(TAG, "Class : " + c.getCanonicalName());
        //获取类限定符
        Log.d(TAG, "Modifiers : " + Modifier.toString(c.getModifiers()));
        //获取类泛型信息
        TypeVariable[] tv = c.getTypeParameters();
        if (tv.length != 0) {
            StringBuilder parameter = new StringBuilder("Parameters : ");
            for (TypeVariable t : tv) {
                parameter.append(t.getName());
                parameter.append(" ");
            }
            Log.d(TAG, parameter.toString());
        } else {
            Log.d(TAG, "  -- No Type Parameters --");
        }
        //获取类实现的所有接口
        Type[] intfs = c.getGenericInterfaces();
        if (intfs.length != 0) {
            StringBuilder interfaces = new StringBuilder("Implemented Interfaces : ");
            for (Type intf : intfs){
                interfaces.append(intf.toString());
                interfaces.append(" ");
            }
            Log.d(TAG, interfaces.toString());
        } else {
            Log.d(TAG, "  -- No Implemented Interfaces --");
        }
        //获取类继承数上的所有父类
        List<Class> l = new ArrayList<>();
        printAncestor(c, l);
        if (l.size() != 0) {
            StringBuilder inheritance = new StringBuilder("Inheritance Path : ");
            for (Class<?> cl : l){
                inheritance.append(cl.getCanonicalName());
                inheritance.append(" ");
            }
            Log.d(TAG, inheritance.toString());
        } else {
            Log.d(TAG, "  -- No Super Classes --%n%n");
        }
        //获取类的注解(只能获取到 RUNTIME 类型的注解)
        Annotation[] ann = c.getAnnotations();
        if (ann.length != 0) {
            StringBuilder annotation = new StringBuilder("Annotations : ");
            for (Annotation a : ann){
                annotation.append(a.toString());
                annotation.append(" ");
            }
            Log.d(TAG, annotation.toString());
        } else {
            Log.d(TAG, "  -- No Annotations --%n%n");
        }
    }
    private static void printAncestor(Class<?> c, List<Class> l) {
        Class<?> ancestor = c.getSuperclass();
        if (ancestor != null) {
            l.add(ancestor);
            printAncestor(ancestor, l);
        }
    }

}
