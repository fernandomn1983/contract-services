package com.nurtricenter.contractservices.configuration;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Command.Middleware;
import jakarta.transaction.Transactional;

@Component
public class TransactionalMiddleware implements Middleware {

    @Override
    @Transactional
    public <R, C extends Command<R>> R invoke(C arg0, Next<R> next) {
        return next.invoke();
    }

}
