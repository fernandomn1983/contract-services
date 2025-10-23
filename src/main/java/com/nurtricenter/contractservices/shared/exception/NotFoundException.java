package com.nurtricenter.contractservices.shared.exception;

public class NotFoundException extends RuntimeException {

    private static final String NOT_FOUND_EXCEPTION_MESSAGE_FORMAT = "No such data found. %s";

    public NotFoundException(String message) {
        super(String.format(NOT_FOUND_EXCEPTION_MESSAGE_FORMAT, message));
    }

}
