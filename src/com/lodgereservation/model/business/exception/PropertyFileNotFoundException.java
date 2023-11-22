package com.lodgereservation.model.business.exception;

import java.io.Serial;

public class PropertyFileNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 3845562249389684073L;  //randomly changed with context action

    public PropertyFileNotFoundException(final String inMsg, final Throwable inNestedException) {
        super(inMsg, inNestedException);
    }
}
