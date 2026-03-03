package com.nurtricenter.contractservices.application.usecase.messaging;

import com.nurtricenter.contractservices.application.messaging.constant.UseCaseConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UnknownMessageCloserOperation extends Operation {

    @Override
    public boolean isProcessable() {
        return true;
    }

    @Override
    public void execute() {
        log.info(UseCaseConstant.UNKNOWN_REQUEST_LOG,
                message.getTransactionId(),
                message.getService(),
                message.getOperation(),
                message.getVerb()
        );
    }

}
