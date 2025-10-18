package com.nurtricenter.contractservices.application.mapper;

import com.nurtricenter.contractservices.domain.aggregatecontract.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.shared.valueobjects.Status;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ContractEntityJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(source = "contractId", target = "id")
    @Mapping(source = "date", target = "contractDate")
    @Mapping(source = "contractStartDate", target = "startDate")
    @Mapping(source = "contractEndDate", target = "endDate")
    @Mapping(source = "contractStatus", target = "status", qualifiedByName = "toContractStatus")
    ContractEntityJpa toEntityJpa(ContractDomain contractDomain);

    @Mapping(source = "id", target = "contractId")
    @Mapping(source = "contractDate", target = "date")
    @Mapping(source = "startDate", target = "contractStartDate")
    @Mapping(source = "endDate", target = "contractEndDate")
    @Mapping(source = "status", target = "contractStatus", qualifiedByName = "toStatus")
    ContractDomain toDomain(ContractEntityJpa contractEntityJpa);

    @Named("toContractStatus")
    default int toContractStatus(Status status) {
        return status != null ? status.getCode() : 0;
    }

    @Named("toStatus")
    default Status toContractStatus(int code) {
        return Status.fromCode(code);
    }

}
