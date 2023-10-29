package com.lodgereservation.model.business;

import com.lodgereservation.model.domain.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Driver {
    public static void main(String[] args) {
        Lodge lodge;
        Reservation res;
        Room room;

        System.out.println("\nLodge Guest Information");

        lodge = new Lodge("Alyeska", "Girdwood, Alaska");
        LodgeGuest arthurDent = new LodgeGuest("Arthur", "Dent", "Cottington, England");

        lodge.addGuest(arthurDent);
        System.out.println(arthurDent);

        System.out.println("\nReservation Information");
        res = new Reservation();
        room = new Room(42, true);
        res.setLodge(lodge);
        res.setDate(Date.valueOf(LocalDate.now()));
        res.setGuest(arthurDent);
        res.setRoom(room);
        System.out.println(res.getLodge().getLodgeName());
        System.out.println(res);

        ReservationComposite composite = new ReservationComposite();
        composite.addUpdate(LocalDateTime.now());
        composite.addUpdate(LocalDateTime.now());
        System.out.println("updates: " + composite);
    }
}