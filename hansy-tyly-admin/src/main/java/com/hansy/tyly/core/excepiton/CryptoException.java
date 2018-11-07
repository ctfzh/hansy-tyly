package com.hansy.tyly.core.excepiton;

/**
 * Created by MIfengHe on 2017/10/23.
 */
public class CryptoException extends BaseException {

    public CryptoException(Throwable e) {
        super(e);
    }

    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(String message, Throwable e) {
        super(message, e);
    }
}
