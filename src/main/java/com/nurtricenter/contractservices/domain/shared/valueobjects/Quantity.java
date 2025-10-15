package com.nurtricenter.contractservices.domain.shared.valueobjects;

import com.nurtricenter.contractservices.domain.shared.exception.InvalidValueException;

public class Quantity {

    private int value;

    public Quantity(int value) throws InvalidValueException {
        this.value = prepareValue(value);

    }

    private int prepareValue(int value) throws InvalidValueException {
        if (value < 0) {
            throw new InvalidValueException("Quantity");
        }

        return value;
    }

}
