package com.nurtricenter.contractservices.shared.exception;

public class InvalidValueException extends RuntimeException {

    private static final String INVALID_VALUE_EXCEPTION_MESSAGE_FORMAT = "Invalid value. %s";

    public InvalidValueException(String cause) {
        super(String.format(INVALID_VALUE_EXCEPTION_MESSAGE_FORMAT, cause));
    }

}
