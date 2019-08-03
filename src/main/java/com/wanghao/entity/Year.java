package com.wanghao.entity;

import com.wanghao.exception.InvalidCronException;
import com.wanghao.regex.YearRegex;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wanghao
 * @description
 * @date 7/31/19 12:01 AM
 */
public class Year {
    private String value;

    private YearRegex regex;

    public Year(String value, YearRegex regex) {
        this.value = value;
        this.regex = regex;
    }

    public String getValue() {
        return value;
    }

    public YearRegex getRegex() {
        return regex;
    }

    public static Year parse(String year) throws InvalidCronException {
        if (StringUtils.isEmpty(year)) {
            throw new InvalidCronException("year invalid: " + year);
        }

        for (YearRegex regex : YearRegex.values()) {
            if (year.matches(regex.getRegEx())) {
                return new Year(year, regex);
            }
        }
        throw new InvalidCronException("year invalid: " + year);
    }

    @Override
    public String toString() {
        return "Year{" +
                "value='" + value + '\'' +
                ", regex=" + regex +
                '}';
    }
}
