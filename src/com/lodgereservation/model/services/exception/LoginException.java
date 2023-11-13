package com.lodgereservation.model.services.exception;

public class LoginException extends Exception {

    private static final long serialVersionUID = 6009821813727278428L;

    public LoginException(final String inMsg) {
        super(inMsg);
    }

    public LoginException(final String inMsg, final Throwable inNestedException) {
        super(inMsg, inNestedException);
    }
}
