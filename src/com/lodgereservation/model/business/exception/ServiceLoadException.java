package com.lodgereservation.model.business.exception;

//@SuppressWarnings("serial")       //redundant?
public class ServiceLoadException extends Exception {

    public ServiceLoadException(final String inMsg, final Throwable inNestedException) {
        super(inMsg, inNestedException);
    }
}
