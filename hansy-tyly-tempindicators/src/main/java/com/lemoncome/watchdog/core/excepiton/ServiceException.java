package com.lemoncome.watchdog.core.excepiton;

/**
 * Created by MIfengHe on 2017/10/23.
 */
public class ServiceException extends BaseException {

    public ServiceException(Throwable e) {
        super(e);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
    }
}
