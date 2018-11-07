package com.lemoncome.watchdog.core.excepiton;

/**
 * Created by MIfengHe on 2017/10/23.
 */
public class ParameterException extends BaseException {

    public ParameterException(Throwable e) {
        super(e);
    }

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, Throwable e) {
        super(message, e);
    }
}
