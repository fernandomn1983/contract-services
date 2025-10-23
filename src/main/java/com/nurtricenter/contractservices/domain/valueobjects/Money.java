package com.nurtricenter.contractservices.domain.valueobjects;

import com.nurtricenter.contractservices.shared.exception.InvalidValueException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {

    private static final int MAX_DECIMALS = 2;

    private final String currency;
    private final BigDecimal amount;
    private final String currencyCode;

    public Money(String currency, String amount, String currencyCode) throws InvalidValueException {
        this.currency = currency;
        this.amount = prepareCurrencyAmount(amount);
        this.currencyCode = currencyCode;
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
