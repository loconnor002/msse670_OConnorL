package com.lodgereservation.model.business;

import com.lodgereservation.model.domain.*;
import com.lodgereservation.model.services.factory.ServiceFactory;
import com.lodgereservation.model.services.reservationService.ReservationServiceImplementation;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Driver {
    public static void main(String[] args) {
        Lodge lodge;
        LodgeGuest guest;
        Room room, room2;
        Reservation res, res2;
        ReservationComposite composite;
        ServiceFactory serviceFactory;
        String password = "default password";   //todo remove wk4
        boolean result;                         //todo remove wk4
        boolean success;                        //todo remove wk4

        // instantiate, initialize, and display a Lodge object
        lodge = new Lodge("Alyeska", "Girdwood");
        //System.out.println("\n" + lodge);

        // instantiate Guest, Room, and Reservation objects and initialize their values
        guest = new LodgeGuest("Arthur", "Dent", "Cottington");
        res = new Reservation();
        room = new Room(42, true);

        // add room and guest to the Reservation object, and set the reservation date
        res.setGuest(guest);
        res.setRoom(room);
        res.setDate(Date.valueOf(LocalDate.now()));

        // display reservation
        //System.out.println(res);

        // store reservation and guest with the lodge
        lodge.addReservation(res);
        lodge.addGuest(guest);

        // instantiate, initialize, and display ReservationComposite object
        composite = new ReservationComposite(guest, res, room);
        composite.addUpdate(LocalDateTime.now(), "test update");
        //System.out.println(composite);

        // create new service factory
        serviceFactory = new ServiceFactory();

        //Demonstrate CRUD operations from services layer (will be replaced by presentation layer in the coming weeks)
        //LoginService
        result = serviceFactory.getLoginService().findUser(composite);
        System.out.println("serviceFactory findUser: " + result);
        if (serviceFactory.getLoginService().authenticateUser(composite, password))
            System.out.println("authenticated " + guest.getFirstName());
        else
            System.out.println("not authenticated " + guest.getFirstName());

        //CREATE reservation
        res2 = serviceFactory.getResService().createReservation();

        //READ reservations
        serviceFactory.getResService().listReservations(lodge);
        guest = new LodgeGuest("Tricia", "McMillan", "Islington");
        res2.setGuest(guest);
        lodge.addGuest(guest);

        //UPDATE reservation room
        room2 = new Room(1, true);
        res2.setRoom(room);
        lodge.addReservation(res2);
        System.out.println(lodge.getReservations());
        success = serviceFactory.getResService().updateReservationRoom(lodge, res2, room2);
        System.out.println("update success: " + success);
        System.out.println(lodge.getReservations());

        //DELETE reservation
        success = serviceFactory.getResService().deleteReservation(lodge, res);
        System.out.println("delete success: " + success);
        System.out.println(lodge.getReservations());
        System.out.println(lodge.getGuests());
    }
}