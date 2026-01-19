package com.nurtricenter.contractservices.unittest.domain.valueobjects;

import com.nurtricenter.contractservices.domain.valueobjects.Quantity;
import com.nurtricenter.contractservices.shared.exception.InvalidValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class QuantityTest {

    private Quantity sut;

    @Test
    void instanceQuantity_whenSendingInvalidValue_throwsInvalidException() {
        int invalidValue = 0;

        Assertions.assertThrows(InvalidValueException.class, () -> {
            sut = new Quantity(invalidValue);
        });
    }

    @Test
    void instanceQuantity_whenSendingValidValue_doesNotThrowException() {
        int validValue = 1;

        Assertions.assertDoesNotThrow(() -> {
            sut = new Quantity(validValue);
        });
    }

}