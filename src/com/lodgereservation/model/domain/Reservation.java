package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.sql.Date;

public class Reservation implements Serializable {

    private static final long RES_ID = -99;                  //todo how to initialize final var?
    private Date date;
    private String lodgeName;       // todo Lodge object?
    private String room;            // todo Room object

    private LodgeGuest guest;
    private Reservation res;

    public Reservation() {

    }

    public String toString() {
        return "RES_ID: " + RES_ID;
    }
}
