package com.lodgereservation.model.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservationComposite {

    private Lodge lodge;
    private LodgeGuest guest;
    private Reservation reservation;
    private Room room;
    private ArrayList<LocalDateTime> updates;

    public ReservationComposite()  {
        lodge = new Lodge();
        guest = new LodgeGuest();
        reservation = new Reservation();
        room = new Room();
        updates = new ArrayList<>();
    }

    public ReservationComposite(Lodge lodge, LodgeGuest guest, Reservation res, Room room) {
        this.lodge = lodge;
        this.guest = guest;
        this.reservation = res;
        this.room = room;
        updates = new ArrayList<LocalDateTime>();
    }

    public void addUpdate(LocalDateTime dt) {
        //todo store nature of update
        updates.add(dt);
    }

    @Override
    public String toString() {
        return updates.toString();
    }
}
