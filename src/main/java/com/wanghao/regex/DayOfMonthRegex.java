package com.wanghao.regex;

/**
 * @author wanghao
 * @description: DayOfMonth正则枚举
 * @date 6/2/19 4:02 PM
 */
public enum DayOfMonthRegex {

    //匹配1-31的整数
    NUMBER(0, "([1-9]|1[0-9]|2[0-9]|3[0-1])"),
    BRIDGE(1, "([1-9]|1[0-9]|2[0-9]|3[0-1])" + "-" + "([1-9]|1[0-9]|2[0-9]|3[0-1])"),
    SLASH(2, "([1-9]|1[0-9]|2[0-9]|3[0-1])" + "/" + "([1-9]|1[0-9]|2[0-9]|3[0-1])"),
    COMMA(3, "([1-9]|1[0-9]|2[0-9]|3[0-1])" + "(," + "([1-9]|1[0-9]|2[0-9]|3[0-1])" + ")+"),
    LAST(4, "([1-9]|1[0-9]|2[0-9]|3[0-1])" + "?L"),
    WORK(5, "([1-9]|1[0-9]|2[0-9]|3[0-1])" + "W"),
    LAST_WORK(6, "LW"),
    STAR(7, "\\*"),
    QUESTION(8, "\\?");

    private int code;

    private String regEx;

    DayOfMonthRegex(int code, String regEx) {
        this.code = code;
        this.regEx = regEx;
    }

    public int getCode() {
        return code;
    }

    public String getRegEx() {
        return regEx;
    }

    private static final String NUMBER_REG = "([1-9]|1[0-9]|2[0-9]|3[0-1])";

    private static final String BRIDGE_REG = NUMBER_REG + "-" + NUMBER_REG;

    private static final String SLASH_REG = NUMBER_REG + "/" + NUMBER_REG;

    private static final String COMMA_REG = NUMBER_REG + "(," + NUMBER_REG + ")+";

    private static final String LAST_REG = NUMBER_REG + "?L";

    private static final String WORK_REG = NUMBER_REG + "W";

    private static final String LAST_WORK_REG = "LW";

    private static final String STAR_REG = "\\*";

    private static final String QUESTION_REG = "\\?";
}
