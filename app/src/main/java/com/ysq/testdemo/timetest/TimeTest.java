package com.ysq.testdemo.timetest;

import java.util.Calendar;
import java.util.Date;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/10/8
 * <p>
 * 包 名：com.ysq.testdemo.timetest
 * <p>
 * 类 名：TimeTest
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class TimeTest {

    /**
     * 日期类 1. Date
     */
    public  void  testDate(){
        Date date1=new Date();    //获取当前日期
        System.out.println(" 这件事发生时间为："+date1);
        try {
            Thread.sleep(6000);//暂停 1 分钟
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        Date date2 = new Date();
        System.out.println("现在时间为："+date2);
        if(date2.before(date1)) {
            System.out.println("你还有 "+(date2.getTime()-date1.getTime())/1000+" 秒需要去完成这件事！");
        } else {
            System.out.println("事情已经过去了 "+(date2.getTime()-date1.getTime())/1000+" 秒");
        }
    }

    /**
     *
     */
    public void testCarlendar(){
        Calendar calendar = Calendar.getInstance();    //如果不设置时间，则默认为当前时间
        calendar.setTime( new Date());    //将系统当前时间赋值给 Calendar 对象
        System.out.println("现在时刻："+calendar.getTime());    //获取当前时间
        int year=calendar.get(Calendar.YEAR);    //获取当前年份
        System.out.println("现在是"+year+"年");
        int month=calendar.get(Calendar.MONTH)+1;    //获取当前月份（月份从 0 开始，所以加 1）
        System.out.print(month+"月");
        int day=calendar.get(Calendar.DATE);    //获取日
        System.out.print(day+"日");
        int week=calendar.get(Calendar.DAY_OF_WEEK)-1;    //获取今天星期几（以星期日为第一天）
        System.out.print("星期"+week);
        int hour=calendar.get(Calendar.HOUR_OF_DAY);    //获取当前小时数（24 小时制）
        System.out.print(hour+"时");
        int minute=calendar.get(Calendar.MINUTE);    //获取当前分钟
        System.out.print(minute+"分");
        int second=calendar.get(Calendar.SECOND);    //获取当前秒数
        System.out.print(second+"秒");
        int millisecond=calendar.get(Calendar.MILLISECOND);    //获取毫秒数
        System.out.print(millisecond+"毫秒");
        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);    //获取今天是本月第几天
        System.out.println("今天是本月的第 "+dayOfMonth+" 天");
        int dayOfWeekInMonth=calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);    //获取今天是本月第几周
        System.out.println("今天是本月第 "+dayOfWeekInMonth+" 周");
        int many=calendar.get(Calendar.DAY_OF_YEAR);    //获取今天是今年第几天
        System.out.println("今天是今年第 "+many+" 天");
        Calendar c=Calendar.getInstance();
        c.set(2019,07,23);    //设置年月日，时分秒将默认采用当前值
        System.out.println("设置日期为 2019-07-23 后的时间："+c.getTime());    //输出时间
    }

}
