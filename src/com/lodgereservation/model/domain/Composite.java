package com.lodgereservation.model.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Composite {

    private Lodge lodge;
    private LodgeGuest guest;
    private Reservation reservation;
    private Room room;

    private ArrayList<LocalDateTime> updates;

    public Composite()  {
        this.lodge = new Lodge();
        this.guest = new LodgeGuest();
        this.reservation = new Reservation();
        this.room = room;
        this.updates = new ArrayList<>();
    }

    public Composite(Lodge l, LodgeGuest g, Reservation res, Room room) {
        this.lodge = l;
        this.guest = g;
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
