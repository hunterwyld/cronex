package com.wanghao.exception;

/**
 * @author wanghao
 * @description 非法的正则表达式
 * @date 7/30/19 5:22 PM
 */
public class InvalidCronException extends Exception {
    private static final long serialVersionUID = -4357896479794027262L;

    public InvalidCronException() {
        super();
    }

    public InvalidCronException(String message) {
        super(message);
    }
}
