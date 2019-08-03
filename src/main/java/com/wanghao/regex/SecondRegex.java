package com.wanghao.regex;

/**
 * @author wanghao
 * @description: Second正则枚举
 * @date 6/2/19 3:54 PM
 */
public enum SecondRegex {

    //匹配0-59的整数
    NUMBER(0, "([0-9]|[1-5][0-9])"),
    BRIDGE(1, "([0-9]|[1-5][0-9])" + "-" + "([0-9]|[1-5][0-9])"),
    SLASH(2, "([0-9]|[1-5][0-9])" + "/" + "([0-9]|[1-5][0-9])"),
    COMMA(3, "([0-9]|[1-5][0-9])" + "(," + "([0-9]|[1-5][0-9])" + ")+"),
    STAR(4, "\\*");


    private int code;

    private String regEx;

    SecondRegex(int code, String regEx) {
        this.code = code;
        this.regEx = regEx;
    }

    public int getCode() {
        return code;
    }

    public String getRegEx() {
        return regEx;
    }

    /* -----second相关----- */
    //匹配0-59的整数
    private static final String NUMBER_REG = "([0-9]|[1-5][0-9])";

    private static final String BRIDGE_REG = NUMBER_REG + "-" + NUMBER_REG;

    private static final String SLASH_REG = NUMBER_REG + "/" + NUMBER_REG;

    private static final String COMMA_REG = NUMBER_REG + "(," + NUMBER_REG + ")+";

    private static final String STAR_REG = "\\*";
}
