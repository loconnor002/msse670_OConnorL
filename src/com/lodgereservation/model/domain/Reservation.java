package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

public class Reservation implements Serializable {

    private final UUID RES_ID;
    private Lodge lodge;
    private Date date;
    private LodgeGuest guest;
    private Room room;

    public Reservation() {
        RES_ID = UUID.randomUUID();
    }

    public Reservation(Date date, LodgeGuest guest, Lodge lodge, Room room) {
        this.RES_ID = UUID.randomUUID();
        this.date = date;
        this.guest = guest;
        this.lodge = lodge;
        this.room = room;
    }

    @Override
    public String toString() {
        return "Reservation{Reservation ID=" + RES_ID +
                ", Date=" + date +
                ", Guest=" + guest +
                ", Room=" + room + "}";
    }

    public UUID getID() {
        return this.RES_ID;
    }

    public Lodge getLodge() {
        return lodge;
    }

    public void setLodge(Lodge lodge) {
        this.lodge = lodge;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LodgeGuest getGuest() {
        return guest;
    }

    public void setGuest(LodgeGuest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
