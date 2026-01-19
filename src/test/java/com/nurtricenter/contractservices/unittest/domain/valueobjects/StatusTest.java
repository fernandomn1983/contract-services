package com.nurtricenter.contractservices.unittest.domain.valueobjects;

import com.nurtricenter.contractservices.domain.valueobjects.Status;
import com.nurtricenter.contractservices.shared.exception.InvalidValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class StatusTest {

    @Test
    void statusFromCode_whenSendingNullValue_throwsInvalidValueException() {
        Integer invalidValue = 0;

        Assertions.assertThrows(InvalidValueException.class, () -> {
            Status.fromCode(invalidValue);
        });
    }

    @Test
    void statusFromCode_whenSendingInvalidValue_throwsInvalidValueException() {
        Integer invalidValue = 0;

        Assertions.assertThrows(InvalidValueException.class, () -> {
            Status.fromCode(invalidValue);
        });
    }

    @Test
    void statusFromCode_whenSendingValidValue_returnsValidStatusCode() {
        Integer validValue = 1;

        Status status = Status.fromCode(validValue);

        Assertions.assertEquals(Status.PREPARED.getCode(), status.getCode());
    }

}