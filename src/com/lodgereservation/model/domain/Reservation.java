package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Reservation implements Serializable {

    private LocalDate date;
    private LodgeGuest guest;
    private Room room;

    public Reservation() {
        guest = new LodgeGuest("fn", "ln", "email", "phone");
        room = new Room(55, true, true);
        date = LocalDate.now();
    }

    public Reservation(LocalDate date, LodgeGuest guest, Room room) {
        this.date = date;
        this.guest = guest;
        this.room = room;
    }

    public Reservation(Composite composite) {
        date = composite.getReservation().getDate();
        guest = composite.getGuest();
        room = composite.getRoom();
    }

    @Override
    public String toString() {
        return "Reservation{Date=" + date +
                ", Guest=" + guest.getFirstName() + " " + guest.getLastName() +
                ", Room=" + room.getRoomNum() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(date, that.date) && Objects.equals(guest, that.guest) && Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, guest, room);
    }

    public LocalDate getDate() {
        return date;
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
