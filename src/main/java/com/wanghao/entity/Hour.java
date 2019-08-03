package com.wanghao.entity;

import com.wanghao.exception.InvalidCronException;
import com.wanghao.regex.HourRegex;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wanghao
 * @description
 * @date 7/30/19 11:52 PM
 */
public class Hour {
    private String value;

    private HourRegex regex;

    public Hour(String value, HourRegex regex) {
        this.value = value;
        this.regex = regex;
    }

    public String getValue() {
        return value;
    }

    public HourRegex getRegex() {
        return regex;
    }

    public static Hour parse(String hour) throws InvalidCronException {
        if (StringUtils.isEmpty(hour)) {
            throw new InvalidCronException("hour invalid: " + hour);
        }

        for (HourRegex regex : HourRegex.values()) {
            if (hour.matches(regex.getRegEx())) {
                return new Hour(hour, regex);
            }
        }
        throw new InvalidCronException("hour invalid: " + hour);
    }

    @Override
    public String toString() {
        return "Hour{" +
                "value='" + value + '\'' +
                ", regex=" + regex +
                '}';
    }
}
