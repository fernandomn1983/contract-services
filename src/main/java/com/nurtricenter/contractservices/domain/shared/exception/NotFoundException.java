package com.nurtricenter.contractservices.domain.shared.exception;

public class NotFoundException extends Exception {

    private static final String NOT_FOUND_EXCEPTION_MESSAGE_FORMAT = "No such data found with name %s";

    public NotFoundException(String message) {
        super(String.format(NOT_FOUND_EXCEPTION_MESSAGE_FORMAT, message));
    }

}
