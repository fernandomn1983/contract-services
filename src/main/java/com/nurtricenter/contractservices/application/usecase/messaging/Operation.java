package com.nurtricenter.contractservices.application.usecase.messaging;

import com.nurtricenter.contractservices.application.messaging.dto.InboundMessageDto;

abstract class Operation<T> {

    protected Operation next;
    protected InboundMessageDto<T> message;

    public void linkWith(Operation next, InboundMessageDto<T> message) {
        this.next = next;
        this.message = message;
    }

    public abstract boolean isProcessable();

    public abstract void execute();

    public void process() {
        if (!isProcessable()) {
            next.process();
        } else {
            execute();
        }
    }

}
