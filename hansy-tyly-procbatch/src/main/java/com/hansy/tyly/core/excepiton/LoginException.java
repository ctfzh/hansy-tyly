package com.hansy.tyly.core.excepiton;

/**
 * Created by MIfengHe on 2017/10/23.
 */
public class LoginException extends BaseException {

    public LoginException(Throwable e) {
        super(e);
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable e) {
        super(message, e);
    }
}
