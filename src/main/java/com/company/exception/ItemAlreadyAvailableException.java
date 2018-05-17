package com.company.exception;

public class ItemAlreadyAvailableException extends RuntimeException {

    private static final long serialVersionUID = -3598487285141254644L;

    public ItemAlreadyAvailableException(String message) {
        super(message);
    }
}
