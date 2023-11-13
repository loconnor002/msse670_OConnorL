package com.lodgereservation.model.services.exception;

public class InventoryException extends Exception {
    private static final long serialVersionUID = 1L;

    public InventoryException(final String inMsg) {
        super(inMsg);
    }
    public InventoryException(final String inMsg, final Throwable inNestedException) {
        super(inMsg, inNestedException);
    }
}
