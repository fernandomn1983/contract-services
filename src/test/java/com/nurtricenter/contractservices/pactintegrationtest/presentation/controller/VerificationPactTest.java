package com.nurtricenter.contractservices.pactintegrationtest.presentation.controller;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@Provider("ContractServiceProvider")
@PactFolder("D:/Fer/Courses/DiplMicroservicios/Repos/contract-services-consumer/target/pacts")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("contract")
@Disabled
public class VerificationPactTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUpPact(PactVerificationContext context) throws Exception {
        context.setTarget(new HttpTestTarget("localhost", port));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTest(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("Microservice is up and running")
    public void systemIsUpAndRunning() {
        System.out.println("Microservice is up and running");
    }

    @State("Create Contract Successfully")
    public void systemCanCreateContractSuccessfully() {
        System.out.println("Create Contract Successfully");
    }

}
