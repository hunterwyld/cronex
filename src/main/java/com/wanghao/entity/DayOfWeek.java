package com.wanghao.entity;

import com.wanghao.exception.InvalidCronException;
import com.wanghao.regex.DayOfWeekRegex;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wanghao
 * @description
 * @date 7/30/19 11:58 PM
 */
public class DayOfWeek {
    private String value;

    private DayOfWeekRegex regex;

    public DayOfWeek(String value, DayOfWeekRegex regex) {
        this.value = value;
        this.regex = regex;
    }

    public String getValue() {
        return value;
    }

    public DayOfWeekRegex getRegex() {
        return regex;
    }

    public static DayOfWeek parse(String dayOfWeek) throws InvalidCronException {
        if (StringUtils.isEmpty(dayOfWeek)) {
            throw new InvalidCronException("dayOfWeek invalid: " + dayOfWeek);
        }

        for (DayOfWeekRegex regex : DayOfWeekRegex.values()) {
            if (dayOfWeek.matches(regex.getRegEx())) {
                return new DayOfWeek(dayOfWeek, regex);
            }
        }
        throw new InvalidCronException("dayOfWeek invalid: " + dayOfWeek);
    }

    @Override
    public String toString() {
        return "DayOfWeek{" +
                "value='" + value + '\'' +
                ", regex=" + regex +
                '}';
    }
}
