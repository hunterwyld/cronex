package com.wanghao.entity;

import com.wanghao.exception.InvalidCronException;
import com.wanghao.regex.SecondRegex;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wanghao
 * @description
 * @date 7/30/19 11:06 PM
 */
public class Second {
    private String value;

    private SecondRegex regex;

    public Second(String value, SecondRegex regex) {
        this.value = value;
        this.regex = regex;
    }

    public String getValue() {
        return value;
    }

    public SecondRegex getRegex() {
        return regex;
    }

    public static Second parse(String second) throws InvalidCronException {
        if (StringUtils.isEmpty(second)) {
            throw new InvalidCronException("second invalid: " + second);
        }

        for (SecondRegex regex : SecondRegex.values()) {
            if (second.matches(regex.getRegEx())) {
                return new Second(second, regex);
            }
        }
        throw new InvalidCronException("second invalid: " + second);
    }

    @Override
    public String toString() {
        return "Second{" +
                "value='" + value + '\'' +
                ", regex=" + regex +
                '}';
    }
}
