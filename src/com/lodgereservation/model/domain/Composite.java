package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * Provides a container for passing domain objects throughout the layered architecture of
 * the LodgeReservation application.
 *
 * <a href="https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html">Future Reference, Javadoc</a>
 */
public class Composite implements Serializable {

    private Lodge lodge;
    private LodgeGuest guest;
    private Reservation reservation;
    private Room room;
    private Room newRoom;
    private HashMap<LocalDateTime, String> updates;

    public Composite(LodgeGuest guest, Reservation res, Room room, Lodge lodge) {
        this.lodge = lodge;
        this.guest = guest;
        this.reservation = res;
        this.room = room;
        newRoom = new Room(-999);
        updates = new HashMap<>();
        this.addUpdate("\nComposite created");
    }

    public Composite(LodgeGuest guest, Reservation res, Room room, Room newRoom, Lodge lodge) {
        this.lodge = lodge;
        this.guest = guest;
        this.reservation = res;
        this.room = room;
        this.newRoom = newRoom;
        updates = new HashMap<>();
        this.addUpdate("\nComposite created");
    }

    public Composite(LodgeGuest guest, Lodge lodge) {
        this.lodge = lodge;
        this.guest = guest;
        this.reservation = new Reservation();
        this.room = new Room();
        this.newRoom = new Room(-999);
        updates = new HashMap<>();
        this.addUpdate("Composite created\n");

    }

    public Composite(LodgeGuest guest, Reservation res, Lodge lodge) {
        this.lodge = lodge;
        this.guest = guest;
        this.reservation = res;
        this.room = new Room();
        newRoom = new Room(-999);
        updates = new HashMap<>();
        this.addUpdate("Composite created\n");
    }

    /**
     * Log updates to this ReservationComposite object.
     *
     * @param comment   description of update
     */
    public void addUpdate(String comment) {
        updates.put(LocalDateTime.now(), comment);
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
        this.addUpdate("Added guest: " + guest + "\n");
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
        this.addUpdate("Added reservation: " + reservation + "\n");
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
        this.addUpdate("Set room: " + room + "\n");
    }

    public Room getNewRoom() { return newRoom; }

    public void setNewRoom(Room newRoom) {
        // todo update if add rooms table to database
        this.newRoom = newRoom;
        this.addUpdate("Set new room: " + newRoom + "\n");
    }

    public HashMap<LocalDateTime, String> getUpdates() {
        return updates;
    }

    public Lodge getLodge() {
        return lodge;
    }

    public void setLodge(Lodge lodge) {
        this.lodge = lodge;
        this.addUpdate("Set lodge: " + lodge + "\n");
    }
}
