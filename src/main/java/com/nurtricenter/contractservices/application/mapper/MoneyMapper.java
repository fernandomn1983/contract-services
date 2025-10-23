package com.nurtricenter.contractservices.application.mapper;

import com.nurtricenter.contractservices.domain.valueobjects.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface MoneyMapper {

    @Mapping(target = "currency", constant = "Bolivianos")
    @Mapping(target = "currencyCode", constant = "Bs.")
    @Mapping(source = "cost", target = "amount")
    Money toMoney(BigDecimal cost);

}
