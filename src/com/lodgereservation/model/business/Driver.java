package com.lodgereservation.model.business;

import com.lodgereservation.model.domain.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Driver {
    public static void main(String[] args) {
        Lodge lodge;
        LodgeGuest guest;
        Room room;
        Reservation res;
        ReservationComposite composite;

        // instantiate, initialize, and display a Lodge object
        lodge = new Lodge("Alyeska", "Girdwood");
        System.out.println("\n" + lodge);

        // instantiate Guest, Room, and Reservation objects and initialize their values
        guest = new LodgeGuest("Arthur", "Dent", "Cottington");
        res = new Reservation();
        room = new Room(42, true);

        // add room and guest to the Reservation object, and set the reservation date
        res.setGuest(guest);
        res.setRoom(room);
        res.setDate(Date.valueOf(LocalDate.now()));

        // display reservation
        System.out.println(res);

        // store reservation and guest with the lodge
        lodge.addReservation(res);
        lodge.addGuest(guest);

        // instantiate, initialize, and display ReservationComposite object
        composite = new ReservationComposite(guest, res, room);
        composite.addUpdate(LocalDateTime.now(), "test update");
        System.out.println(composite);
    }
}