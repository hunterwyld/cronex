package com.wanghao.regex;

/**
 * @author wanghao
 * @description: DayOfWeek正则枚举
 * @date 6/2/19 4:02 PM
 */
public enum DayOfWeekRegex {

    //匹配1-7的整数
    NUMBER(0, "([1-7])"),
    DAY(1, "(SUN)|(MON)|(TUE)|(WED)|(THU)|(FRI)|(SAT)"),
    BRIDGE_NUMBER(2, "([1-7])" + "-" + "([1-7])"),
    BRIDGE_DAY(3, "((SUN)|(MON)|(TUE)|(WED)|(THU)|(FRI)|(SAT))" + "-" + "((SUN)|(MON)|(TUE)|(WED)|(THU)|(FRI)|(SAT))"),
    SLASH_NUMBER(4, "(([1-7])|(\\*))?" + "/" + "([1-7])"),
    SLASH_DAY(5, "(((SUN)|(MON)|(TUE)|(WED)|(THU)|(FRI)|(SAT))|(\\*))?" + "/" + "([1-7])"),
    COMMA_NUMBER(6, "([1-7])" + "(," + "([1-7])" + ")+"),
    COMMA_DAY(7, "((SUN)|(MON)|(TUE)|(WED)|(THU)|(FRI)|(SAT))" + "(," + "((SUN)|(MON)|(TUE)|(WED)|(THU)|(FRI)|(SAT))" + ")+"),
    LAST_NUMBER(8, "([1-7])" + "?L"),
    LAST_DAY(9, "((SUN)|(MON)|(TUE)|(WED)|(THU)|(FRI)|(SAT))" + "?L"),
    WELL(10, "([1-7])" + "#" + "([1-5])"),
    STAR(11, "\\*"),
    QUESTION(12, "\\?");

    private int code;

    private String regEx;

    DayOfWeekRegex(int code, String regEx) {
        this.code = code;
        this.regEx = regEx;
    }

    public int getCode() {
        return code;
    }

    public String getRegEx() {
        return regEx;
    }

    private static final String NUMBER_REG = "([1-7])";

    private static final String DAY_REG = "(SUN)|(MON)|(TUE)|(WED)|(THU)|(FRI)|(SAT)";

    private static final String BRIDGE_NUMBER_REG = NUMBER_REG + "-" + NUMBER_REG;

    private static final String BRIDGE_DAY_REG = DAY_REG + "-" + DAY_REG;

    private static final String SLASH_NUMBER_REG = NUMBER_REG + "/" + NUMBER_REG;

    private static final String SLASH_DAY_REG = DAY_REG + "/" + DAY_REG;

    private static final String COMMA_NUMBER_REG = NUMBER_REG + "(," + NUMBER_REG + ")+";

    private static final String COMMA_DAY_REG = DAY_REG + "(," + DAY_REG + ")+";

    private static final String LAST_NUMBER_REG = NUMBER_REG + "?L";

    private static final String LAST_DAY_REG = DAY_REG + "?L";

    private static final String WELL_REG = NUMBER_REG + "#" + "([1-5])";

    private static final String STAR_REG = "\\*";

    private static final String QUESTION_REG = "\\?";
}
