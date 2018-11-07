package com.lemoncome.watchdog.core.excepiton;

/**
 * Created by MIfengHe on 2017/10/23.
 */
public class UnauthorizedException extends BaseException {

    public UnauthorizedException(Throwable e) {
        super(e);
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable e) {
        super(message, e);
    }
}
