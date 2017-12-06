package com.winterchen.exception;

/**
 * 传参为空异常类
 * Created by winterchen on 2017/11/29.
 */
public class ArgumentIsNullException extends RuntimeException {

    public ArgumentIsNullException(String message) {
        super(message);
    }

    public ArgumentIsNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
