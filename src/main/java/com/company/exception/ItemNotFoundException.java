package com.company.exception;

public class ItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5865487284751254785L;

    public ItemNotFoundException(String message) {
        super(message);
    }
}
