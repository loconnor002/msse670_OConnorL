package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Composite implements Serializable {

    private Lodge lodge;
    private LodgeGuest guest;
    private Reservation reservation;
    private Room room;
    private Room newRoom;
    private HashMap<LocalDateTime, String> updates;

    public Composite() {
        lodge = new Lodge();
        guest = new LodgeGuest();
        reservation = new Reservation();
        room = new Room();
        newRoom = new Room(-999);
        updates = new HashMap<>();
    }

    public Composite(LodgeGuest guest, Reservation res, Room room, Lodge lodge) {
        this.lodge = lodge;
        this.guest = guest;
        this.reservation = res;
        this.room = room;
        newRoom = new Room(-999);
        updates = new HashMap<>();
    }

    public Composite(LodgeGuest guest, Reservation res, Room room, Room newRoom, Lodge lodge) {
        this.lodge = lodge;
        this.guest = guest;
        this.reservation = res;
        this.room = room;
        this.newRoom = newRoom;
        updates = new HashMap<>();
    }

    public Composite(LodgeGuest guest, Lodge lodge) {
        this.lodge = lodge;
        this.guest = guest;
        this.reservation = new Reservation();
        this.room = new Room();
        this.newRoom = new Room(-999);
        updates = new HashMap<>();
    }

    public Composite(LodgeGuest guest, Reservation res, Lodge lodge) {
        this.lodge = lodge;
        this.guest = guest;
        this.reservation = res;
        this.room = new Room();
        newRoom = new Room(-999);
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

    public Room getNewRoom() { return newRoom; }

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
