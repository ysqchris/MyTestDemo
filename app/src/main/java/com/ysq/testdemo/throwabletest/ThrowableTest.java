package com.ysq.testdemo.throwabletest;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/10/8
 * <p>
 * 包 名：com.ysq.testdemo.throwabletest
 * <p>
 * 类 名：ThrowableTest
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：测试异常处理逻辑
 */
public class ThrowableTest {

       public  boolean testEx() throws Exception {
            boolean ret = true;
            try {
                ret = testEx1();
            } catch (Exception e) {
                System.out.println("testEx, catch exception");
                ret = false;
                throw e;
            } finally {
                System.out.println("testEx, finally; return value=" + ret);
                return ret;
            }
        }

        boolean testEx1() throws Exception {
            boolean ret = true;
            try {
                ret = testEx2();
                if (!ret) {
                    return false;
                }
                System.out.println("testEx1, at the end of try");
                return ret;
            } catch (Exception e) {
                System.out.println("testEx1, catch exception");
                ret = false;
                throw e;
            } finally {
                System.out.println("testEx1, finally; return value=" + ret);
                return ret;
            }
        }

        boolean testEx2() throws Exception {
            boolean ret = true;
            try {
                int b = 12;
                int c;
                for (int i = 2; i >= -2; i--) {
                    c = b / i;
                    System.out.println("i=" + i);
                }
                return true;
            } catch (Exception e) {
                System.out.println("testEx2, catch exception");
                ret = false;
                throw e;
            } finally {
                System.out.println("testEx2, finally; return value=" + ret);
                return ret;
            }
        }


        public void testThrowRuntimeException() throws  RuntimeException{

        }
}
