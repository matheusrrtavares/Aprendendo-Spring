package com.matheus.aprendendoSpring.infrastructure.exceptions;

import ch.qos.logback.core.helpers.ThrowableToStringArray;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String msg){
        super(msg);
    }

    public ResourceNotFoundException(String msg, Throwable throwable){
        super(msg, throwable);
    }

}
