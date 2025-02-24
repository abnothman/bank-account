package com.example.exception;

/**
 * defined Exception for null account value
 */
public class NullAccountException extends RuntimeException {
    public NullAccountException(String message) {
        super(message);
    }
}
