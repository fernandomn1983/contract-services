package com.nurtricenter.contractservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ContractServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContractServicesApplication.class, args);
    }
}
