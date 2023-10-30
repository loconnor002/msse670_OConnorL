package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

public class ReservationComposite implements Serializable {

    private LodgeGuest guest;
    private Reservation reservation;
    private Room room;
    private HashMap<LocalDateTime, String> updates;

    public ReservationComposite() {
        guest = new LodgeGuest();
        reservation = new Reservation();
        room = new Room();
        updates = new HashMap<>();
    }

    public ReservationComposite(LodgeGuest guest, Reservation res, Room room) {
        this.guest = guest;
        this.reservation = res;
        this.room = room;
        updates = new HashMap<>();
    }

    /**
     * Log updates to this ReservationComposite object.
     *
     * @param dt        date and time of update
     * @param comment   description of update
     */
    public void addUpdate(LocalDateTime dt, String comment) {
        updates.put(dt, comment);
    }

    @Override
    public String toString() {
        return "\nReservationComposite Info: " +
                "\n" + guest +
                "\n" + reservation +
                "\n" + room +
                "\nupdates: " + updates;
    }
}
