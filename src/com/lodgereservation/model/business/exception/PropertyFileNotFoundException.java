package com.lodgereservation.model.business.exception;

public class PropertyFileNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public PropertyFileNotFoundException(final String inMsg, final Throwable inNestedException) {
        super(inMsg, inNestedException);
    }
}
