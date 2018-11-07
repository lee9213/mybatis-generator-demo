package com.giants.common.exception;

/**
 * @author libo
 * @date 2018/11/7 14:59
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, String cause) {

    }
}
