package com.wanghao.regex;

/**
 * @author wanghao
 * @description: Hour正则枚举
 * @date 6/2/19 3:55 PM
 */
public enum HourRegex {

    //匹配0-23的整数
    NUMBER(0, "([0-9]|1[0-9]|2[0-3])"),
    BRIDGE(1, "([0-9]|1[0-9]|2[0-3])" + "-" + "([0-9]|1[0-9]|2[0-3])"),
    SLASH(2, "([0-9]|1[0-9]|2[0-3])" + "/" + "([0-9]|1[0-9]|2[0-3])"),
    COMMA(3, "([0-9]|1[0-9]|2[0-3])" + "(," + "([0-9]|1[0-9]|2[0-3])" + ")+"),
    STAR(4, "\\*");

    private int code;

    private String regEx;

    HourRegex(int code, String regEx) {
        this.code = code;
        this.regEx = regEx;
    }

    public int getCode() {
        return code;
    }

    public String getRegEx() {
        return regEx;
    }

    /* -----hour相关----- */
    private static final String NUMBER_REG = "([0-9]|1[0-9]|2[0-3])";

    private static final String BRIDGE_REG = NUMBER_REG + "-" + NUMBER_REG;

    private static final String SLASH_REG = NUMBER_REG + "/" + NUMBER_REG;

    private static final String COMMA_REG = NUMBER_REG + "(," + NUMBER_REG + ")+";

    private static final String STAR_REG = "\\*";
}
