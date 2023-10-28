package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.sql.Date;

import static com.lodgereservation.model.domain.Lodge.NEXT_RES_ID;

public class Reservation implements Serializable {

    private final long RES_ID;
    private Lodge lodge;
    private Date date;
    private LodgeGuest guest;
    private Room room;

    public Reservation() {
        this.RES_ID = NEXT_RES_ID.getAndIncrement();
    }

    public Reservation(Date d, LodgeGuest g, Lodge l, Room r) {
        this.RES_ID = NEXT_RES_ID.getAndIncrement();
        this.date = d;
        this.guest = g;
        this.lodge = l;
        this.room = r;
    }

    @Override
    public String toString() {
        return "Reservation RES_ID: " + RES_ID +
                " Date: " + date +
                " Guest: " + guest +
                " Room: " + room;
    }

    public long getRES_ID() {
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
