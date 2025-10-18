package com.nurtricenter.contractservices.domain.shared.valueobjects;

import com.nurtricenter.contractservices.domain.shared.exception.InvalidValueException;

public enum Status {

    PREPARED(1, "Preparado"),
    PAID(2, "Pagado");

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

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
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
