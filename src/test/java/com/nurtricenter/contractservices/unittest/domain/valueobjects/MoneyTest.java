package com.nurtricenter.contractservices.unittest.domain.valueobjects;

import com.nurtricenter.contractservices.domain.valueobjects.Money;
import com.nurtricenter.contractservices.shared.exception.InvalidValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@Tag("unit")
class MoneyTest {

    private Money sut;

    @Test
    void createInstance_whenSendingInvalidValue_throwsInvalidException() {
        BigDecimal invalidValue = new BigDecimal("-1");

        Assertions.assertThrows(InvalidValueException.class, () -> {
            sut = new Money(invalidValue);
        });
    }

    @Test
    void createInstance_whenSendingValidValue_doesNotThrowException() {
        BigDecimal validValue = new BigDecimal("100");

        Assertions.assertDoesNotThrow(() -> {
            sut = new Money(validValue);
        });
    }

}