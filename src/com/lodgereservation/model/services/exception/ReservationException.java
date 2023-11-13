package com.lodgereservation.model.services.exception;

public class ReservationException extends Exception {

    private static final long serialVersionUID = 1L;

    public ReservationException(final String inMsg, final Throwable inNestedException) {
        super(inMsg, inNestedException);
    }
}
