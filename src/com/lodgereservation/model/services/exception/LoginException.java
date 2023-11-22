package com.lodgereservation.model.services.exception;

import java.io.Serial;

public class LoginException extends Exception {

    @Serial
    private static final long serialVersionUID = -1900724481290143169L;

    public LoginException(final String inMsg) {
        super(inMsg);
    }

    public LoginException(final String inMsg, final Throwable inNestedException) {
        super(inMsg, inNestedException);
    }
}
