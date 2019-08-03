package com.wanghao.util;

import com.wanghao.Cron;
import com.wanghao.entity.DayOfMonth;
import com.wanghao.entity.DayOfWeek;
import com.wanghao.entity.Hour;
import com.wanghao.entity.Minute;
import com.wanghao.entity.Month;
import com.wanghao.entity.Second;
import com.wanghao.entity.Year;
import com.wanghao.regex.DayOfMonthRegex;
import com.wanghao.regex.DayOfWeekRegex;
import com.wanghao.exception.InvalidCronException;
import org.apache.commons.lang3.StringUtils;


/**
 * @author wanghao
 * @description: cron表达式解析工具类
 * @date 5/30/19 8:04 PM
 */
public class ParseUtil {

    private static final int CRON_LENGTH_6 = 6;
    private static final int CRON_LENGTH_7 = 7;

    public static Cron parse(String input) throws InvalidCronException {
        if (StringUtils.isEmpty(input)) {
            throw new InvalidCronException("input is empty");
        }

        String[] strArray = input.split(" ");
        if (strArray.length != CRON_LENGTH_6 && strArray.length != CRON_LENGTH_7) {
            throw new InvalidCronException("invalid length: " + strArray.length);
        }

        Second second = Second.parse(strArray[0]);
        Minute minute = Minute.parse(strArray[1]);
        Hour hour = Hour.parse(strArray[2]);
        DayOfMonth dayOfMonth = DayOfMonth.parse(strArray[3]);
        Month month = Month.parse(strArray[4]);
        DayOfWeek dayOfWeek = DayOfWeek.parse(strArray[5]);

        if (!DayOfMonthRegex.QUESTION.equals(dayOfMonth.getRegex()) && !DayOfWeekRegex.QUESTION.equals(dayOfWeek.getRegex())) {
            throw new InvalidCronException("DayOfMonth and DayOfWeek can not be set simultaneously. " +
                    "DayOfMonth: " + dayOfMonth + ", DayOfWeek: " + dayOfWeek);
        }

        Cron cron;
        if (strArray.length == CRON_LENGTH_6) {
            cron = new Cron(second, minute, hour, dayOfMonth, month, dayOfWeek);
        } else {
            Year year = Year.parse(strArray[6]);
            cron = new Cron(second, minute, hour, dayOfMonth, month, dayOfWeek, year);
        }

        return cron;
    }

}
