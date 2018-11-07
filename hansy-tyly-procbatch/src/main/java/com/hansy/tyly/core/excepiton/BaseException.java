package com.hansy.tyly.core.excepiton;

/**
 * Created by MIfengHe on 2017/10/23.
 */
public class BaseException extends RuntimeException {

    public BaseException(Throwable e) {
        super(e);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable e) {
        super(message, e);
    }
}
