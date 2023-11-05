package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

public class ReservationComposite implements Serializable {

    private Lodge lodge;
    private LodgeGuest guest;
    private Reservation reservation;
    private Room room;
    private HashMap<LocalDateTime, String> updates;

    public ReservationComposite() {
        lodge = new Lodge();
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

    public LodgeGuest getGuest() {
        return this.guest;
    }

    public void setGuest(LodgeGuest guest) {
        this.guest = guest;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public HashMap<LocalDateTime, String> getUpdates() {
        return updates;
    }

    public void setUpdates(HashMap<LocalDateTime, String> updates) {
        this.updates = updates;
    }

    public Lodge getLodge() {
        return lodge;
    }

    public void setLodge(Lodge lodge) {
        this.lodge = lodge;
    }
}
