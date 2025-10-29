package com.nurtricenter.contractservices.domain.valueobjects;

import com.nurtricenter.contractservices.shared.exception.InvalidValueException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {

    private static final int MAX_DECIMALS = 2;
    private static final String DEFAULT_CURRENCY = "BOLIVIANOS";
    private static final String DEFAULT_CURRENCY_CODE = "BOB";

    private final String currency;
    private final BigDecimal amount;
    private final String currencyCode;

    public Money(BigDecimal amount) throws InvalidValueException {
        this.currency = DEFAULT_CURRENCY;
        this.amount = amount.setScale(MAX_DECIMALS, RoundingMode.HALF_UP);
        this.currencyCode = DEFAULT_CURRENCY_CODE;
    }

    private BigDecimal prepareCurrencyAmount(String amount) throws InvalidValueException {
        BigDecimal moneyAmount = new BigDecimal(amount);

        if (moneyAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidValueException("Money");
        }

        return moneyAmount.setScale(MAX_DECIMALS, RoundingMode.HALF_UP);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getShortMoney() {
        return currencyCode + " " + amount.toString();
    }

    public String getExtendedCurrency() {
        return currency + " " + amount.toString();
    }

}
