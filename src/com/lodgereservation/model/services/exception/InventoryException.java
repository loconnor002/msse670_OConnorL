package com.lodgereservation.model.services.exception;

import java.io.Serial;

public class InventoryException extends Exception {
    @Serial
    private static final long serialVersionUID = -6171977230116470984L;

    public InventoryException(final String inMsg) {
        super(inMsg);
    }


    public InventoryException(final String inMsg, final Throwable inNestedException) {
        super(inMsg, inNestedException);
    }
}
