package com.wanghao.entity;

import com.wanghao.exception.InvalidCronException;
import com.wanghao.regex.MonthRegex;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wanghao
 * @description
 * @date 7/30/19 11:56 PM
 */
public class Month {
    private String value;

    private MonthRegex regex;

    public Month(String value, MonthRegex regex) {
        this.value = value;
        this.regex = regex;
    }

    public String getValue() {
        return value;
    }

    public MonthRegex getRegex() {
        return regex;
    }

    public static Month parse(String month) throws InvalidCronException {
        if (StringUtils.isEmpty(month)) {
            throw new InvalidCronException("month invalid: " + month);
        }

        for (MonthRegex regex : MonthRegex.values()) {
            if (month.matches(regex.getRegEx())) {
                return new Month(month, regex);
            }
        }
        throw new InvalidCronException("month invalid: " + month);
    }

    @Override
    public String toString() {
        return "Month{" +
                "value='" + value + '\'' +
                ", regex=" + regex +
                '}';
    }
}
