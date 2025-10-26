package com.nurtricenter.contractservices.domain.valueobjects;

import com.nurtricenter.contractservices.shared.exception.InvalidValueException;

public enum Status {

    PREPARED(1, "Preparado"),
    PAID(2, "Pagado"),
    CANCELED(3, "Cancelado");

    private static final String INVALID_CODE_MSG_FORMAT_EXCEPTION = "Status Code = %s";

    private Integer code;
    private String description;

    Status(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Status fromCode(Integer code) {
        if (code == null) {
            throw new InvalidValueException(String.format(INVALID_CODE_MSG_FORMAT_EXCEPTION, code));
        }

        for (Status status : Status.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }

        throw new InvalidValueException(String.format(INVALID_CODE_MSG_FORMAT_EXCEPTION, code));
    }

}
