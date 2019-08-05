package com.wanghao;

import com.wanghao.entity.DayOfMonth;
import com.wanghao.entity.DayOfWeek;
import com.wanghao.entity.Hour;
import com.wanghao.entity.Minute;
import com.wanghao.entity.Month;
import com.wanghao.entity.Second;
import com.wanghao.entity.Year;
import com.wanghao.exception.InvalidCronException;
import com.wanghao.regex.DayOfMonthRegex;
import com.wanghao.regex.DayOfWeekRegex;
import com.wanghao.regex.HourRegex;
import com.wanghao.regex.MinuteRegex;
import com.wanghao.regex.MonthRegex;
import com.wanghao.regex.SecondRegex;
import com.wanghao.regex.YearRegex;
import com.wanghao.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wanghao
 * @description:        cron表达式: second minute hour dayOfMonth month dayOfWeek Year 或
 *                                 second minute hour dayOfMonth month dayOfWeek
 * @date 5/30/19 7:48 PM
 */
public class Cron {
    private Second second;

    private Minute minute;

    private Hour hour;

    private DayOfMonth dayOfMonth;

    private Month month;

    private DayOfWeek dayOfWeek;

    private Year year;

    public Cron(Second second, Minute minute, Hour hour, DayOfMonth dayOfMonth, Month month, DayOfWeek dayOfWeek) {
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    public Cron(Second second, Minute minute, Hour hour, DayOfMonth dayOfMonth, Month month, DayOfWeek dayOfWeek, Year year) {
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.year = year;
    }

    public boolean matches(Date time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time);
        calendar.set(Calendar.MILLISECOND, 0);

        int second = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int year = calendar.get(Calendar.YEAR);
        //System.out.println(second + " " + minute + " " + hour + " " + dayOfMonth + " " + month + " " + dayOfWeek + " " + year);
        try {
            if (!matchSecond(second)) {
                return false;
            }
            if (!matchMinute(minute)) {
                return false;
            }
            if (!matchHour(hour)) {
                return false;
            }
            if (!matchDayOfMonth(dayOfMonth, month, year)) {
                return false;
            }
            if (!matchMonth(month)) {
                return false;
            }
            if (!matchDayOfWeek(dayOfWeek, month, year, dayOfMonth)) {
                return false;
            }
            if (!matchYear(year)) {
                return false;
            }
        } catch (InvalidCronException e) {
            return false;
        }

        return true;
    }

    private boolean matchSecond(int second) throws InvalidCronException {
        boolean match = false;
        String secondValue = this.second.getValue();
        SecondRegex secondRegex = this.second.getRegex();
        if (secondRegex.equals(SecondRegex.STAR)) {
            match = true;
        } else if (secondRegex.equals(SecondRegex.NUMBER)) {
            int a = Integer.parseInt(secondValue);
            match = second == a;
        } else if (secondRegex.equals(SecondRegex.BRIDGE)) {
            String[] arr = secondValue.split("-");
            int min = Integer.parseInt(arr[0]);
            int max = Integer.parseInt(arr[1]);
            if (min > max) {
                throw new InvalidCronException("second invalid: " + secondValue);
            }
            match = second <= max && second >= min;
        } else if (secondRegex.equals(SecondRegex.SLASH)) {
            String[] arr = secondValue.split("/");
            int left = StringUtils.equals(arr[0], "")||StringUtils.equals(arr[0], "*") ? 0 : Integer.parseInt(arr[0]);
            int right = Integer.parseInt(arr[1]);
            Set<Integer> possible = new HashSet<>();
            int start = left;
            while (start <= 59) {
                possible.add(start);
                start += right;
            }
            match = possible.contains(second);
        } else if (secondRegex.equals(SecondRegex.COMMA)) {
            String[] arr = secondValue.split(",");
            for (String str : arr) {
                int a = Integer.parseInt(str);
                if (second == a) {
                    match = true;
                    break;
                }
            }
        }
        return match;
    }

    private boolean matchMinute(int minute) throws InvalidCronException {
        boolean match = false;
        String minuteValue = this.minute.getValue();
        MinuteRegex minuteRegex = this.minute.getRegex();
        if (minuteRegex.equals(MinuteRegex.STAR)) {
            match = true;
        } else if (minuteRegex.equals(MinuteRegex.NUMBER)) {
            int a = Integer.parseInt(minuteValue);
            match = minute == a;
        } else if (minuteRegex.equals(MinuteRegex.BRIDGE)) {
            String[] arr = minuteValue.split("-");
            int min = Integer.parseInt(arr[0]);
            int max = Integer.parseInt(arr[1]);
            if (min > max) {
                throw new InvalidCronException("minute invalid: " + minuteValue);
            }
            match = minute <= max && minute >= min;
        } else if (minuteRegex.equals(MinuteRegex.SLASH)) {
            String[] arr = minuteValue.split("/");
            int left = StringUtils.equals(arr[0], "")||StringUtils.equals(arr[0], "*") ? 0 : Integer.parseInt(arr[0]);
            int right = Integer.parseInt(arr[1]);
            Set<Integer> possible = new HashSet<>();
            int start = left;
            while (start <= 59) {
                possible.add(start);
                start += right;
            }
            match = possible.contains(minute);
        } else if (minuteRegex.equals(MinuteRegex.COMMA)) {
            String[] arr = minuteValue.split(",");
            for (String str : arr) {
                int a = Integer.parseInt(str);
                if (minute == a) {
                    match = true;
                    break;
                }
            }
        }
        return match;
    }

    private boolean matchHour(int hour) throws InvalidCronException {
        boolean match = false;
        String hourValue = this.hour.getValue();
        HourRegex hourRegex = this.hour.getRegex();
        if (hourRegex.equals(HourRegex.STAR)) {
            match = true;
        } else if (hourRegex.equals(HourRegex.NUMBER)) {
            int a = Integer.parseInt(hourValue);
            match = hour == a;
        } else if (hourRegex.equals(HourRegex.BRIDGE)) {
            String[] arr = hourValue.split("-");
            int min = Integer.parseInt(arr[0]);
            int max = Integer.parseInt(arr[1]);
            if (min > max) {
                throw new InvalidCronException("hour invalid: " + hourValue);
            }
            match = hour <= max && hour >= min;
        } else if (hourRegex.equals(HourRegex.SLASH)) {
            String[] arr = hourValue.split("/");
            int left = StringUtils.equals(arr[0], "")||StringUtils.equals(arr[0], "*") ? 0 : Integer.parseInt(arr[0]);
            int right = Integer.parseInt(arr[1]);
            Set<Integer> possible = new HashSet<>();
            int start = left;
            while (start <= 23) {
                possible.add(start);
                start += right;
            }
            match = possible.contains(hour);
        } else if (hourRegex.equals(HourRegex.COMMA)) {
            String[] arr = hourValue.split(",");
            for (String str : arr) {
                int a = Integer.parseInt(str);
                if (hour == a) {
                    match = true;
                    break;
                }
            }
        }
        return match;
    }

    private boolean matchDayOfMonth(int dayOfMonth, int month, int year) throws InvalidCronException {
        boolean match = false;
        String dayOfMonthValue = this.dayOfMonth.getValue();
        DayOfMonthRegex dayOfMonthRegex = this.dayOfMonth.getRegex();
        if (dayOfMonthRegex.equals(DayOfMonthRegex.STAR)) {
            match = true;
        } else if (dayOfMonthRegex.equals(DayOfMonthRegex.NUMBER)) {
            int a = Integer.parseInt(dayOfMonthValue);
            match = dayOfMonth == a;
        } else if (dayOfMonthRegex.equals(DayOfMonthRegex.BRIDGE)) {
            String[] arr = dayOfMonthValue.split("-");
            int min = Integer.parseInt(arr[0]);
            int max = Integer.parseInt(arr[1]);
            if (min > max) {
                throw new InvalidCronException("dayOfMonth invalid: " + dayOfMonthValue);
            }
            match = dayOfMonth <= max && dayOfMonth >= min;
        } else if (dayOfMonthRegex.equals(DayOfMonthRegex.SLASH)) {
            String[] arr = dayOfMonthValue.split("/");
            int left = StringUtils.equals(arr[0], "")||StringUtils.equals(arr[0], "*") ? 1 : Integer.parseInt(arr[0]);
            int right = Integer.parseInt(arr[1]);
            Set<Integer> possible = new HashSet<>();
            int start = left;
            while (start <= 31) {
                possible.add(start);
                start += right;
            }
            match = possible.contains(dayOfMonth);
        } else if (dayOfMonthRegex.equals(DayOfMonthRegex.COMMA)) {
            String[] arr = dayOfMonthValue.split(",");
            for (String str : arr) {
                int a = Integer.parseInt(str);
                if (dayOfMonth == a) {
                    match = true;
                    break;
                }
            }
        } else if (dayOfMonthRegex.equals(DayOfMonthRegex.LAST)) {
            int L_idx = dayOfMonthValue.indexOf('L');
            int a = 1;
            if (L_idx > 0) {
                a = Integer.parseInt(dayOfMonthValue.split("L")[0]);
            }
            int dayCount = DateUtil.getDayCountOfMonth(year, month);
            match = dayCount + 1 - a == dayOfMonth;
        } else if (dayOfMonthRegex.equals(DayOfMonthRegex.WORK)) {
            int a = Integer.parseInt(dayOfMonthValue.split("W")[0]);
            int nearestWorkDay = DateUtil.getNearestWorkDay(year, month, a);
            match = dayOfMonth == nearestWorkDay;
        } else if (dayOfMonthRegex.equals(DayOfMonthRegex.LAST_WORK)) {
            int lastWorkDay = DateUtil.getLastWorkDay(year, month);
            match = dayOfMonth == lastWorkDay;
        } else if (dayOfMonthRegex.equals(DayOfMonthRegex.QUESTION)) {
            match = true;//TODO
        }
        return match;
    }

    private boolean matchMonth(int month) throws InvalidCronException {
        boolean match = false;
        String monthValue = this.month.getValue();
        MonthRegex monthRegex = this.month.getRegex();
        if (monthRegex.equals(MonthRegex.STAR)) {
            match = true;
        } else if (monthRegex.equals(MonthRegex.NUMBER)) {
            int a = Integer.parseInt(monthValue);
            match = month == a;
        } else if (monthRegex.equals(MonthRegex.MONTH)) {
            int a = DateUtil.getMonthByName(monthValue);
            match = month == a;
        } else if (monthRegex.equals(MonthRegex.BRIDGE_NUMBER)) {
            String[] arr = monthValue.split("-");
            int min = Integer.parseInt(arr[0]);
            int max = Integer.parseInt(arr[1]);
            if (min > max) {
                throw new InvalidCronException("month invalid: " + monthValue);
            }
            match = month <= max && month >= min;
        } else if (monthRegex.equals(MonthRegex.BRIDGE_MONTH)) {
            String[] arr = monthValue.split("-");
            int min = DateUtil.getMonthByName(arr[0]);
            int max = DateUtil.getMonthByName(arr[1]);
            if (min > max) {
                throw new InvalidCronException("month invalid: " + monthValue);
            }
            match = month <= max && month >= min;
        } else if (monthRegex.equals(MonthRegex.SLASH_NUMBER)) {
            String[] arr = monthValue.split("/");
            int left = StringUtils.equals(arr[0], "")||StringUtils.equals(arr[0], "*") ? 1 : Integer.parseInt(arr[0]);
            int right = Integer.parseInt(arr[1]);
            Set<Integer> possible = new HashSet<>();
            int start = left;
            while (start <= 12) {
                possible.add(start);
                start += right;
            }
            match = possible.contains(month);
        } else if (monthRegex.equals(MonthRegex.SLASH_MONTH)) {
            String[] arr = monthValue.split("/");
            int left = StringUtils.equals(arr[0], "")||StringUtils.equals(arr[0], "*") ? 1 : DateUtil.getMonthByName(arr[0]);
            int right = Integer.parseInt(arr[1]);
            Set<Integer> possible = new HashSet<>();
            int start = left;
            while (start <= 12) {
                possible.add(start);
                start += right;
            }
            match = possible.contains(month);
        } else if (monthRegex.equals(MonthRegex.COMMA_NUMBER)) {
            String[] arr = monthValue.split(",");
            for (String str : arr) {
                int a = Integer.parseInt(str);
                if (month == a) {
                    match = true;
                    break;
                }
            }
        } else if (monthRegex.equals(MonthRegex.COMMA_MONTH)) {
            String[] arr = monthValue.split(",");
            for (String str : arr) {
                int a = DateUtil.getMonthByName(str);
                if (month == a) {
                    match = true;
                    break;
                }
            }
        }
        return match;
    }

    private boolean matchDayOfWeek(int dayOfWeek, int month, int year, int dayOfMonth) throws InvalidCronException {
        boolean match = false;
        String dayOfWeekValue = this.dayOfWeek.getValue();
        DayOfWeekRegex dayOfWeekRegex = this.dayOfWeek.getRegex();
        if (dayOfWeekRegex.equals(DayOfWeekRegex.STAR)) {
            match = true;
        } else if (dayOfWeekRegex.equals(DayOfWeekRegex.NUMBER)) {
            int a = Integer.parseInt(dayOfWeekValue);
            match = dayOfWeek == a;
        } else if (dayOfWeekRegex.equals(DayOfWeekRegex.DAY)) {
            int day = DateUtil.getDayByName(dayOfWeekValue);
            match = dayOfWeek == day;
        } else if (dayOfWeekRegex.equals(DayOfWeekRegex.BRIDGE_NUMBER)) {
            String[] arr = dayOfWeekValue.split("-");
            int min = Integer.parseInt(arr[0]);
            int max = Integer.parseInt(arr[1]);
            if (min > max) {
                throw new InvalidCronException("dayOfWeek invalid: " + dayOfWeekValue);
            }
            match = dayOfWeek <= max && dayOfWeek >= min;
        } else if (dayOfWeekRegex.equals(DayOfWeekRegex.BRIDGE_DAY)) {
            String[] arr = dayOfWeekValue.split("-");
            int min = DateUtil.getDayByName(arr[0]);
            int max = DateUtil.getDayByName(arr[1]);
            if (min > max) {
                throw new InvalidCronException("dayOfWeek invalid: " + dayOfWeekValue);
            }
            match = dayOfWeek <= max && dayOfWeek >= min;
        } else if (dayOfWeekRegex.equals(DayOfWeekRegex.SLASH_NUMBER)) {
            String[] arr = dayOfWeekValue.split("/");
            int left = StringUtils.equals(arr[0], "")||StringUtils.equals(arr[0], "*") ? 1 : Integer.parseInt(arr[0]);
            int right = Integer.parseInt(arr[1]);
            Set<Integer> possible = new HashSet<>();
            int start = left;
            while (start <= 7) {
                possible.add(start);
                start += right;
            }
            match = possible.contains(dayOfWeek);
        } else if (dayOfWeekRegex.equals(DayOfWeekRegex.SLASH_DAY)) {
            String[] arr = dayOfWeekValue.split("/");
            int left = StringUtils.equals(arr[0], "")||StringUtils.equals(arr[0], "*") ? 1 : DateUtil.getDayByName(arr[0]);
            int right = Integer.parseInt(arr[1]);
            Set<Integer> possible = new HashSet<>();
            int start = left;
            while (start <= 7) {
                possible.add(start);
                start += right;
            }
            match = possible.contains(dayOfWeek);
        } else if (dayOfWeekRegex.equals(DayOfWeekRegex.COMMA_NUMBER)) {
            String[] arr = dayOfWeekValue.split(",");
            for (String str : arr) {
                int a = Integer.parseInt(str);
                if (dayOfWeek == a) {
                    match = true;
                    break;
                }
            }
        } else if (dayOfWeekRegex.equals(DayOfWeekRegex.COMMA_DAY)) {
            String[] arr = dayOfWeekValue.split(",");
            for (String str : arr) {
                int a = DateUtil.getDayByName(str);
                if (dayOfWeek == a) {
                    match = true;
                    break;
                }
            }
        } else if (dayOfWeekRegex.equals(DayOfWeekRegex.LAST_NUMBER)) {
            int L_idx = dayOfWeekValue.indexOf('L');
            int a = 1;
            if (L_idx > 0) {
                a = Integer.parseInt(dayOfWeekValue.split("L")[0]);
            }
            int lastDayOfWeek = DateUtil.getLastDayOfWeek(year, month, a);
            match = dayOfMonth == lastDayOfWeek;
        } else if (dayOfWeekRegex.equals(DayOfWeekRegex.LAST_DAY)) {
            int L_idx = dayOfWeekValue.indexOf('L');
            int a = 1;
            if (L_idx > 0) {
                a = DateUtil.getDayByName(dayOfWeekValue.split("L")[0]);
            }
            int lastDayOfWeek = DateUtil.getLastDayOfWeek(year, month, a);
            match = dayOfMonth == lastDayOfWeek;
        } else if (dayOfWeekRegex.equals(DayOfWeekRegex.WELL)) {
            String[] arr = dayOfWeekValue.split("#");
            int a = Integer.parseInt(arr[0]);
            int k = Integer.parseInt(arr[1]);
            int kthDayOfWeek = DateUtil.getKthDayOfWeek(year, month, k, a);
            match = dayOfMonth == kthDayOfWeek;
        } else if (dayOfWeekRegex.equals(DayOfWeekRegex.QUESTION)) {
            match = true;//TODO
        }
        return match;
    }

    private boolean matchYear(int year) throws InvalidCronException {
        if (this.year == null) {
            return true;
        }

        boolean match = false;
        String yearValue = this.year.getValue();
        YearRegex yearRegex = this.year.getRegex();
        if (yearRegex.equals(YearRegex.STAR)) {
            match = true;
        } else if (yearRegex.equals(YearRegex.NUMBER)) {
            int a = Integer.parseInt(yearValue);
            match = year == a;
        } else if (yearRegex.equals(YearRegex.BRIDGE)) {
            String[] arr = yearValue.split("-");
            int min = Integer.parseInt(arr[0]);
            int max = Integer.parseInt(arr[1]);
            if (min > max) {
                throw new InvalidCronException("year invalid: " + yearValue);
            }
            match = year <= max && year >= min;
        } else if (yearRegex.equals(YearRegex.SLASH)) {
            String[] arr = yearValue.split("/");
            int left = StringUtils.equals(arr[0], "")||StringUtils.equals(arr[0], "*") ? 1970 : Integer.parseInt(arr[0]);
            int right = Integer.parseInt(arr[1]);
            Set<Integer> possible = new HashSet<>();
            int start = left;
            while (start <= 2099) {
                possible.add(start);
                start += right;
            }
            match = possible.contains(year);
        } else if (yearRegex.equals(YearRegex.COMMA)) {
            String[] arr = yearValue.split(",");
            for (String str : arr) {
                int a = Integer.parseInt(str);
                if (year == a) {
                    match = true;
                    break;
                }
            }
        }
        return match;
    }

    @Override
    public String toString() {
        return "Cron{"
                + second + " "
                + minute + " "
                + hour + " "
                + dayOfMonth + " "
                + month + " "
                + dayOfWeek + " "
                + year + "}";
    }
}
