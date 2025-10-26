package com.nurtricenter.contractservices.application.repository;

import com.nurtricenter.contractservices.application.mapper.ContractMapper;
import com.nurtricenter.contractservices.domain.contract.ContractDomain;
import com.nurtricenter.contractservices.domain.contract.ContractRepository;
import com.nurtricenter.contractservices.domain.service.ServiceDomain;
import com.nurtricenter.contractservices.domain.valueobjects.Status;
import com.nurtricenter.contractservices.shared.exception.NotFoundException;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ContractEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ContractServiceEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.PatientEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity.ServiceEntityJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.ContractRepositoryJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.PatientRepositoryJpa;
import com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository.ServiceRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ContractRepositoryImpl implements ContractRepository {

    private static final String PATIENT_ID_MSG_FORMAT_EXCEPTION = "PatientId = %s";
    private static final String SERVICE_ID_MSG_FORMAT_EXCEPTION = "ServiceIds = %s";
    private static final String CONTRACT_ID_MSG_FORMAT_EXCEPTION = "ContractId = %s";

    private final PatientRepositoryJpa patientRepositoryJpa;
    private final ServiceRepositoryJpa serviceRepositoryJpa;
    private final ContractRepositoryJpa contractRepositoryJpa;
    private final ContractMapper contractMapper;

    @Override
    public ContractDomain createContract(ContractDomain contractDomain) {
        Optional<PatientEntityJpa> optionalPatientEntityJpa = patientRepositoryJpa
                .findById(contractDomain.getPatientDomain().getPatientId());
        if (optionalPatientEntityJpa.isEmpty()) {
            throw new NotFoundException(
                    String.format(PATIENT_ID_MSG_FORMAT_EXCEPTION, contractDomain.getPatientDomain().getPatientId()));
        }

        HashMap<UUID, Integer> serviceDomainMap = new HashMap<>();
        for (ServiceDomain serviceDomain : contractDomain.getServiceDomainList()) {
            serviceDomainMap.putIfAbsent(serviceDomain.getServiceId(), serviceDomain.getQuantity().getValue());
        }

        Set<UUID> serviceUuids = contractDomain.getServiceDomainList()
                .stream()
                .map(ServiceDomain::getServiceId)
                .collect(Collectors.toSet());
        List<ServiceEntityJpa> serviceEntityJpaList = serviceRepositoryJpa.findAllById(serviceUuids);
        if (serviceEntityJpaList.isEmpty() || serviceEntityJpaList.size() != serviceUuids.size()) {
            throw new NotFoundException(String.format(SERVICE_ID_MSG_FORMAT_EXCEPTION, serviceUuids.toString()));
        }

        ContractEntityJpa contractEntityJpaForCreation = contractMapper.toEntityJpa(contractDomain);
        contractEntityJpaForCreation.setPatient(optionalPatientEntityJpa.get());
        contractEntityJpaForCreation.setServices(
                prepareContractServiceEntityJpa(contractEntityJpaForCreation, serviceEntityJpaList, serviceDomainMap));

        ContractEntityJpa contractEntityJpa = contractRepositoryJpa.save(contractEntityJpaForCreation);

        return contractMapper.toDomain(contractEntityJpa);
    }

    private List<ContractServiceEntityJpa> prepareContractServiceEntityJpa(
            ContractEntityJpa contractEntityJpa,
            List<ServiceEntityJpa> serviceEntityJpaList,
            HashMap<UUID, Integer> serviceDomainMap) {
        return serviceEntityJpaList.stream()
                .map(serviceEntityJpa -> {
                    ContractServiceEntityJpa contractServiceEntityJpa = new ContractServiceEntityJpa();
                    contractServiceEntityJpa.setServiceId(serviceEntityJpa);
                    contractServiceEntityJpa.setContractId(contractEntityJpa);
                    contractServiceEntityJpa.setQuantity(serviceDomainMap.get(serviceEntityJpa.getId()));

                    return contractServiceEntityJpa;
                })
                .toList();
    }

    @Override
    public ContractDomain getContract(Integer contractId) {
        Optional<ContractEntityJpa> optionalContractEntityJpa = contractRepositoryJpa.findById(contractId);
        if (optionalContractEntityJpa.isEmpty()) {
            throw new NotFoundException(String.format(CONTRACT_ID_MSG_FORMAT_EXCEPTION, contractId));
        }

        return contractMapper.toDomain(optionalContractEntityJpa.get());
    }

    @Override
    public void cancelContract(Integer contractId) {
        Optional<ContractEntityJpa> optionalContractEntityJpa = contractRepositoryJpa.findPreparedContractByContractId(contractId);
        if (optionalContractEntityJpa.isEmpty()) {
            throw new NotFoundException(String.format(CONTRACT_ID_MSG_FORMAT_EXCEPTION, contractId));
        }

        ContractEntityJpa contractEntityJpa = optionalContractEntityJpa.get();
        contractEntityJpa.setStatus(Status.CANCELED.getCode());

        contractRepositoryJpa.save(contractEntityJpa);
    }

}
