package com.wanghao.regex;

/**
 * @author wanghao
 * @description: Month正则枚举
 * @date 6/2/19 4:02 PM
 */
public enum MonthRegex {

    //匹配1-12的整数
    NUMBER(0, "([1-9]|1[0-2])"),
    MONTH(1, "(JAN)|(FEB)|(MAR)|(APR)|(MAY)|(JUN)|(JUL)|(AUG)|(SEP)|(OCT)|(NOV)|(DEC)"),
    BRIDGE_NUMBER(2, "([1-9]|1[0-2])" + "-" + "([1-9]|1[0-2])"),
    BRIDGE_MONTH(3, "((JAN)|(FEB)|(MAR)|(APR)|(MAY)|(JUN)|(JUL)|(AUG)|(SEP)|(OCT)|(NOV)|(DEC))" + "-" + "((JAN)|(FEB)|(MAR)|(APR)|(MAY)|(JUN)|(JUL)|(AUG)|(SEP)|(OCT)|(NOV)|(DEC))"),
    SLASH_NUMBER(4, "(([1-9]|1[0-2])|(\\*))?" + "/" + "([1-9]|1[0-2])"),
    SLASH_MONTH(5, "(((JAN)|(FEB)|(MAR)|(APR)|(MAY)|(JUN)|(JUL)|(AUG)|(SEP)|(OCT)|(NOV)|(DEC))|(\\*))?" + "/" + "([1-9]|1[0-2])"),
    COMMA_NUMBER(6, "([1-9]|1[0-2])" + "(," + "([1-9]|1[0-2])" + ")+"),
    COMMA_MONTH(7, "((JAN)|(FEB)|(MAR)|(APR)|(MAY)|(JUN)|(JUL)|(AUG)|(SEP)|(OCT)|(NOV)|(DEC))" + "(," + "((JAN)|(FEB)|(MAR)|(APR)|(MAY)|(JUN)|(JUL)|(AUG)|(SEP)|(OCT)|(NOV)|(DEC))" + ")+"),
    STAR(8, "\\*");


    private int code;

    private String regEx;

    MonthRegex(int code, String regEx) {
        this.code = code;
        this.regEx = regEx;
    }

    public int getCode() {
        return code;
    }

    public String getRegEx() {
        return regEx;
    }

    /* -----month相关----- */
    private static final String NUMBER_REG = "([1-9]|1[0-2])";

    private static final String MONTH_REG = "(JAN)|(FEB)|(MAR)|(APR)|(MAY)|(JUN)|(JUL)|(AUG)|(SEP)|(OCT)|(NOV)|(DEC)";

    private static final String BRIDGE_NUMBER_REG = NUMBER_REG + "-" + NUMBER_REG;

    private static final String BRIDGE_MONTH_REG = MONTH_REG + "-" + MONTH_REG;

    private static final String SLASH_NUMBER_REG = NUMBER_REG + "/" + NUMBER_REG;

    private static final String SLASH_MONTH_REG = MONTH_REG + "/" + MONTH_REG;

    private static final String COMMA_NUMBER_REG = NUMBER_REG + "(," + NUMBER_REG + ")+";

    private static final String COMMA_MONTH_REG = MONTH_REG + "(," + MONTH_REG + ")+";

    private static final String STAR_REG = "\\*";
}
