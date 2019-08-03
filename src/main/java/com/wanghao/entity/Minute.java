package com.wanghao.entity;

import com.wanghao.exception.InvalidCronException;
import com.wanghao.regex.MinuteRegex;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wanghao
 * @description
 * @date 7/30/19 11:25 PM
 */
public class Minute {
    private String value;

    private MinuteRegex regex;

    public Minute(String value, MinuteRegex regex) {
        this.value = value;
        this.regex = regex;
    }

    public String getValue() {
        return value;
    }

    public MinuteRegex getRegex() {
        return regex;
    }

    public static Minute parse(String minute) throws InvalidCronException {
        if (StringUtils.isEmpty(minute)) {
            throw new InvalidCronException("minute invalid: " + minute);
        }

        for (MinuteRegex regex : MinuteRegex.values()) {
            if (minute.matches(regex.getRegEx())) {
                return new Minute(minute, regex);
            }
        }
        throw new InvalidCronException("minute invalid: " + minute);
    }

    @Override
    public String toString() {
        return "Minute{" +
                "value='" + value + '\'' +
                ", regex=" + regex +
                '}';
    }
}
