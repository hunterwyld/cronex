package com.wanghao.util;

import com.wanghao.exception.InvalidCronException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wanghao
 * @description 日期工具类
 * @date 7/30/19 7:56 PM
 */
public class DateUtil {
    private static final int JAN = 1;
    private static final int FEB = 2;
    private static final int MAR = 3;
    private static final int APR = 4;
    private static final int MAY = 5;
    private static final int JUN = 6;
    private static final int JUL = 7;
    private static final int AUG = 8;
    private static final int SEP = 9;
    private static final int OCT = 10;
    private static final int NOV = 11;
    private static final int DEC = 12;

    private static Map<Integer, Integer> dayCountMap;
    static {
        dayCountMap = new HashMap<>(11);
        dayCountMap.put(JAN, 31);
        dayCountMap.put(MAR, 31);
        dayCountMap.put(APR, 30);
        dayCountMap.put(MAY, 31);
        dayCountMap.put(JUN, 30);
        dayCountMap.put(JUL, 31);
        dayCountMap.put(AUG, 31);
        dayCountMap.put(SEP, 30);
        dayCountMap.put(OCT, 31);
        dayCountMap.put(NOV, 30);
        dayCountMap.put(DEC, 31);
    }

    private static final int SUN = 1;
    private static final int MON = 2;
    private static final int TUE = 3;
    private static final int WED = 4;
    private static final int THU = 5;
    private static final int FRI = 6;
    private static final int SAT = 7;

    private static Set<Integer> workingDay;
    static {
        workingDay = new HashSet<>(5);
        workingDay.add(MON);
        workingDay.add(TUE);
        workingDay.add(WED);
        workingDay.add(THU);
        workingDay.add(FRI);
    }

    private static Map<String, Integer> nameDayMap;
    static {
        nameDayMap = new HashMap<>(7);
        nameDayMap.put("SUN", SUN);
        nameDayMap.put("MON", MON);
        nameDayMap.put("TUE", TUE);
        nameDayMap.put("WED", WED);
        nameDayMap.put("THU", THU);
        nameDayMap.put("FRI", FRI);
        nameDayMap.put("SAT", SAT);
    }

    private static Map<String, Integer> nameMonthMap;
    static {
        nameMonthMap = new HashMap<>(12);
        nameMonthMap.put("JAN", JAN);
        nameMonthMap.put("FEB", FEB);
        nameMonthMap.put("MAR", MAR);
        nameMonthMap.put("APR", APR);
        nameMonthMap.put("MAY", MAY);
        nameMonthMap.put("JUN", JUN);
        nameMonthMap.put("JUL", JUL);
        nameMonthMap.put("AUG", AUG);
        nameMonthMap.put("SEP", SEP);
        nameMonthMap.put("OCT", OCT);
        nameMonthMap.put("NOV", NOV);
        nameMonthMap.put("DEC", DEC);
    }


    /** 判断是否闰年 */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /** 返回某年某月一共有多少天 */
    public static int getDayCountOfMonth(int year, int month) {
        if (month != FEB) {
            return dayCountMap.get(month);
        }

        if (isLeapYear(year)) {
            return 29;
        }

        return 28;
    }

    /** 返回距离某日期最近的工作日的日期（不跨月查找），比如：
     * 输入2019-08-01，返回1
     * 输入2019-08-31，返回30 */
    public static int getNearestWorkDay(int year, int month, int dayOfMonth) throws InvalidCronException {
        int dayCountOfMonth = getDayCountOfMonth(year, month);
        if (dayOfMonth > dayCountOfMonth) {
            throw new InvalidCronException("month " + month + " doesn't have so many days: " + dayOfMonth);
        }

        Calendar calendar = new GregorianCalendar(year, month-1, dayOfMonth);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (workingDay.contains(dayOfWeek)) {
            return dayOfMonth;
        }
        if (dayOfWeek == SUN) {
            if (dayOfMonth == dayCountOfMonth) {
                return dayOfMonth - 2;
            }
            return dayOfMonth + 1;
        }
        if (dayOfWeek == SAT) {
            if (dayOfMonth == 1) {
                return dayOfMonth + 2;
            }
            return dayOfMonth -1;
        }

        //impossible reach here
        return -1;
    }

    /** 返回某年某月最后一个工作日的日期，比如：
     *  输入2019-08，返回30 */
    public static int getLastWorkDay(int year, int month) {
        int dayCountOfMonth = getDayCountOfMonth(year, month);
        Calendar calendar = new GregorianCalendar(year, month-1, dayCountOfMonth);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (workingDay.contains(dayOfWeek)) {
            return dayCountOfMonth;
        }
        if (dayOfWeek == SUN) {
            return dayCountOfMonth - 2;
        }
        if (dayOfWeek == SAT) {
            return dayCountOfMonth - 1;
        }

        //impossible reach here
        return -1;
    }

    /** 根据对应星期的序号，比如：
     *  输入SUN，返回1 */
    public static int getDayByName(String name) throws InvalidCronException {
        Integer day = nameDayMap.get(name);
        if (day == null) {
            throw new InvalidCronException("invalid name of day: " + name);
        }
        return day;
    }

    /** 返回某年某月最后一个星期几对应的日期，比如：
     *  输入2019年8月，targetDayOfWeek是1表示最后一个星期日，返回25 */
    public static int getLastDayOfWeek(int year, int month, int targetDayOfWeek) {
        int dayCountOfMonth = getDayCountOfMonth(year, month);
        Calendar calendar = new GregorianCalendar(year, month-1, dayCountOfMonth);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == targetDayOfWeek) {
            return dayCountOfMonth;
        }
        int gap = dayOfWeek - targetDayOfWeek;
        if (gap < 0) {
            gap += 7;
        }
        return dayCountOfMonth - gap;
    }

    /** 返回某年某月第k个星期几对应的日期，比如：
     *  2019年8月第4个星期五是23号，此时k=4,targetDayOfWeek=6(星期五) */
    public static int getKthDayOfWeek(int year, int month, int k, int targetDayOfWeek) throws InvalidCronException {
        //先得到本月最后一个targetDayOfWeek的日期
        int lastDayOfWeek = getLastDayOfWeek(year, month, targetDayOfWeek);

        int count = 1;
        int i = lastDayOfWeek;
        while (i -7 >= 1) {
            i -= 7;
            count++;
        }
        //本月第一个targetDayOfWeek的日期
        int firstDayOfWeek = i;

        if (k > count) {
            throw new InvalidCronException("date:" + year + "-" + month + " doesn't have so many dayOfWeek:" + targetDayOfWeek);
        }

        return firstDayOfWeek + 7*(k-1);
    }

    /** 根据对应月份的序号，比如：
     *  输入JAN，返回1 */
    public static int getMonthByName(String name) throws InvalidCronException {
        Integer month = nameMonthMap.get(name);
        if (month == null) {
            throw new InvalidCronException("invalid name of month: " + name);
        }
        return month;
    }

}
