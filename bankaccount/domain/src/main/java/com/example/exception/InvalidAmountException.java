package com.example.exception;

/**
 * defined Exception for invalid transaction amount
 */
public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String message) {
        super(message);
    }

}
