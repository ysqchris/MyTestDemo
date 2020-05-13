package com.ysq.testdemo.test;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/12/5
 * <p>
 * 包 名：com.ysq.testdemo.test
 * <p>
 * 类 名：Cat
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class Cat extends TestClass{

    @Override
    public void testExtend() {
        super.testExtend();
    }

    public static final String TAG = Cat.class.getSimpleName();
    private String name;
    @Deprecated
    public int age;

    public Cat(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public void eat(String food){
        Log.d(TAG, "eat food " + food);
    }

    public void eat(String... foods){
        StringBuilder s = new StringBuilder();
        for(String food : foods){
            s.append(food);
            s.append(" ");
        }
        Log.d(TAG, "eat food " + s.toString());
    }

    public void sleep(){
        Log.d(TAG, "sleep");
    }

    @Override
    public String toString() {
        return "name = " + name + " age = " + age;
    }





    /**
     *
     *      Class 提供了4种方法获得给定类的 Field
     *     1. getDeclaredField(String name) // 获取指定的变量（只要是声明的变量都能获得，包括 private）
     *     2. getField(String name) //获取指定的变量（只能获得 public 的）
     *     3. getDeclaredFields()  获取所有声明的变量（包括 private）
     *     4. getFields()  获取所有的 public 变量

     *     log:
     *     变量名字 = age 类型 = int 修饰符 = public 注解 = @java.lang.Deprecated()
     *     变量名字 = name 类型 = class java.lang.String 修饰符 = private  -- 没有注解 --
     *     变量名字 = TAG 类型 = class java.lang.String 修饰符 = public static final  -- 没有注解 --
     *
     */

    //获取变量类型、修饰符、注解
    public static void testGetField(){
        Class c = Cat.class;
        Field[] fields = c.getDeclaredFields();
        for(Field f : fields){
            StringBuilder builder = new StringBuilder();
            //获取名称
            builder.append("变量名字 = ");
            builder.append(f.getName());
            //获取类型
            builder.append(" 类型 = ");
            builder.append(f.getType());
            //获取修饰符
            builder.append(" 修饰符 = ");
            builder.append(Modifier.toString(f.getModifiers()));
            //获取注解
            Annotation[] ann = f.getAnnotations();
            if (ann.length != 0) {
                builder.append(" 注解 = ");
                for (Annotation a : ann){
                    builder.append(a.toString());
                    builder.append(" ");
                }
            } else {
                builder.append("  -- 没有注解 --");
            }
            Log.d(TAG, builder.toString());
        }
    }

    //获取、设置变量值
    public  static  void testSetField(){
        Cat cat = new Cat("Tom", 2);
        Class c = cat.getClass();
        try {
            //注意获取private变量时，需要用getDeclaredField
            Field fieldName = c.getDeclaredField("name");
            Field fieldAge = c.getField("age");
            //反射获取名字, 年龄
            String name = (String) fieldName.get(cat);
            int age = fieldAge.getInt(cat);
            Log.d(TAG, "before set, Cat name = " + name + " age = " + age);
            //反射重新set名字和年龄
            fieldName.set(cat, "Timmy");
            fieldAge.setInt(cat, 3);
            Log.d(TAG, "after set, Cat " + cat.toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取 Method
     *
     * Class 依然提供了4种方法获取 Method:
     *
     * getDeclaredMethod(String name, Class<?>... parameterTypes)
     *
     * 根据方法名获得指定的方法， 参数 name 为方法名，参数 parameterTypes 为方法的参数类型，如 getDeclaredMethod(“eat”, String.class)
     *
     * getMethod(String name, Class<?>... parameterTypes)
     *
     * 根据方法名获取指定的 public 方法，其它同上
     *
     * getDeclaredMethods()
     *
     * 获取所有声明的方法
     *
     * getMethods()
     *
     * 获取所有的 public 方法
     *
     * 注意： 获取带参数方法时，如果参数类型错误会报 NoSuchMethodException，对于参数是泛型的情况，泛型须当成Object处理（Object.class）
     */


    /**
     * 获取方法返回类型
     *
     * getReturnType()   获取目标方法返回类型对应的 Class 对象
     *
     * getGenericReturnType()  获取目标方法返回类型对应的 Type 对象
     *
     * 这两个方法有啥区别呢？
     *
     * getReturnType()返回类型为 Class，getGenericReturnType() 返回类型为 Type; Class 实现 Type。
     *
     * 返回值为普通简单类型如 Object, int, String 等，getGenericReturnType() 返回值和 getReturnType() 一样
     *
     * 例如 public String function1()，那么各自返回值为：
     *
     * getReturnType() : class java.lang.String
     *
     * getGenericReturnType() : class java.lang.String
     *
     * 返回值为泛型
     *
     * 例如 public T function2()，那么各自返回值为：
     *
     * getReturnType() : class java.lang.Object
     *
     * getGenericReturnType() : T
     *
     * 返回值为参数化类型
     *
     * 例如public Class<String> function3()，那么各自返回值为：
     *
     * getReturnType() : class java.lang.Class
     *
     * getGenericReturnType() : java.lang.Class<java.lang.String>
     *
     * 其实反射中所有形如 getGenericXXX()的方法规则都与上面所述类似。
     */


    /**
     * 数组
     * class [B    //byte类型一维数组
     * class [S    //short类型一维数组
     * class [I    //int类型一维数组
     * class [C    //char类型一维数组
     * class [J    //long类型一维数组，J代表long类型，因为L被引用对象类型占用了
     * class [F    //float类型一维数组
     * class [D    //double类型一维数组
     * class [Lcom.dada.Season    //引用类型一维数组
     * class [[Ljava.lang.String  //引用类型二维数组
     */
    public  void getType(Field pField) {
        //获取一个变量的类型
        Class<?> c = pField.getType();
        //判断该变量是否为数组
        if (c.isArray()) {
            //获取数组的元素类型
            c.getComponentType();
        }
    }

    /**
     * 创建和初始化数组
     *
     * Java 反射为我们提供了 java.lang.reflect.Array 类用来创建和初始化数组。
     *
     * //创建数组， 参数componentType为数组元素的类型，后面不定项参数的个数代表数组的维度，参数值为数组长度
     *         Array.newInstance(Class<?> componentType , int... dimensions);
     *
     *         //设置数组值，array为数组对象，index为数组的下标，value为需要设置的值
     *         Array.set(Object array, int index, int value)
     *
     *         //获取数组的值，array为数组对象，index为数组的下标
     *         Array.get(Object array, int index)
     *
     *         例子,用反射创建 int[] array = new int[]{1, 2}
     */
    public void initArray(){
        Object array = Array.newInstance(int.class, 2);
        Array.setInt(array , 0, 1);
        Array.setInt(array , 1, 2);
    }







}
