package com.broker.error;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.util.ErrorHandler;

public class CustomErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        throw new AmqpRejectAndDontRequeueException(t);
    }
}
