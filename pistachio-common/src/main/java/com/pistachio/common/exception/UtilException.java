package com.pistachio.common.exception;

/**
 * @author: Pengsy
 * @date: 2023/08/01 16:27
 * @description: 工具类异常
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
