package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

public class Reservation implements Serializable {

    private final UUID RES_ID;                                          //todo static?
    private Date date;
    private LodgeGuest guest;
    private Room room;

    public Reservation() {
        RES_ID = UUID.randomUUID();
    }

    public Reservation(Date date, LodgeGuest guest, Room room) {
        this.RES_ID = UUID.randomUUID();
        this.date = date;
        this.guest = guest;
        this.room = room;
    }

    public Reservation(Composite composite) {
        RES_ID = composite.getReservation().getID();
        date = composite.getReservation().getDate();
        guest = composite.getGuest();
        room = composite.getRoom();
    }

    @Override
    public String toString() {
        return "Reservation{ID=" + RES_ID +
                ", Date=" + date +
                ", Guest=" + guest.getFirstName() + " " + guest.getLastName() +
                ", Room=" + room.getRoomNum() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(RES_ID, that.RES_ID) && Objects.equals(date, that.date) && Objects.equals(guest, that.guest) && Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(RES_ID, date, guest, room);
    }

    public UUID getID() {
        return this.RES_ID;
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
