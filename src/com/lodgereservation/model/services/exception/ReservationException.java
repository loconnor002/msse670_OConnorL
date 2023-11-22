package com.lodgereservation.model.services.exception;

import java.io.Serial;

public class ReservationException extends Exception {

    @Serial
    private static final long serialVersionUID = -8270019409452132546L;

    public ReservationException(final String inMsg) {
        super(inMsg);
    }


    public ReservationException(final String inMsg, final Throwable inNestedException) {
        super(inMsg, inNestedException);
    }
}
