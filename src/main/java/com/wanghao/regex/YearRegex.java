package com.wanghao.regex;

/**
 * @author wanghao
 * @description: Year正则枚举
 * @date 6/2/19 4:02 PM
 */
public enum YearRegex {

    //匹配1970-2099的整数
    NUMBER(0, "(19[7-9][0-9]|20[0-9][0-9])"),
    BRIDGE(1, "(19[7-9][0-9]|20[0-9][0-9])" + "-" + "(19[7-9][0-9]|20[0-9][0-9])"),
    SLASH(2, "(19[7-9][0-9]|20[0-9][0-9])" + "/" + "(19[7-9][0-9]|20[0-9][0-9])"),
    COMMA(3, "(19[7-9][0-9]|20[0-9][0-9])" + "(," + "(19[7-9][0-9]|20[0-9][0-9])" + ")+"),
    STAR(4, "\\*");

    private int code;

    private String regEx;

    YearRegex(int code, String regEx) {
        this.code = code;
        this.regEx = regEx;
    }

    public int getCode() {
        return code;
    }

    public String getRegEx() {
        return regEx;
    }

    /* -----year相关----- */
    //匹配1970-2099的整数
    private static final String NUMBER_REG = "(19[7-9][0-9]|20[0-9][0-9])";

    private static final String BRIDGE_REG = NUMBER_REG + "-" + NUMBER_REG;

    private static final String SLASH_REG = NUMBER_REG + "/" + NUMBER_REG;

    private static final String COMMA_REG = NUMBER_REG + "(," + NUMBER_REG + ")+";

    private static final String STAR_REG = "\\*";
}
