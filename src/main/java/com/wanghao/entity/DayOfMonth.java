package com.wanghao.entity;

import com.wanghao.exception.InvalidCronException;
import com.wanghao.regex.DayOfMonthRegex;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wanghao
 * @description
 * @date 7/30/19 11:54 PM
 */
public class DayOfMonth {
    private String value;

    private DayOfMonthRegex regex;

    public DayOfMonth(String value, DayOfMonthRegex regex) {
        this.value = value;
        this.regex = regex;
    }

    public String getValue() {
        return value;
    }

    public DayOfMonthRegex getRegex() {
        return regex;
    }

    public static DayOfMonth parse(String dayOfMonth) throws InvalidCronException {
        if (StringUtils.isEmpty(dayOfMonth)) {
            throw new InvalidCronException("dayOfMonth invalid: " + dayOfMonth);
        }

        for (DayOfMonthRegex regex : DayOfMonthRegex.values()) {
            if (dayOfMonth.matches(regex.getRegEx())) {
                return new DayOfMonth(dayOfMonth, regex);
            }
        }
        throw new InvalidCronException("dayOfMonth invalid: " + dayOfMonth);
    }

    @Override
    public String toString() {
        return "DayOfMonth{" +
                "value='" + value + '\'' +
                ", regex=" + regex +
                '}';
    }
}
