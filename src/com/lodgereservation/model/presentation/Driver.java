package com.lodgereservation.model.presentation;

import com.lodgereservation.model.business.manager.LodgeReservationManager;
import com.lodgereservation.model.domain.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Driver {
    public static void main(String[] args) {
        boolean success;
        Lodge lodge;
        LodgeGuest guest;
        Reservation res;
        LodgeReservationManager manager;
        Composite composite;

        //wk5 instantiate & configure Composite obj, pass it to services, print returned output from methods
        lodge = new Lodge("Alyeska", "Girdwood");
        guest = new LodgeGuest("Arthur", "Dent", "Cottington");

        //add 10 rooms to lodge, (roomNumber, available=true, clean=true)
        for (int i = 0; i < 10; i++) {
            lodge.getRooms().add(new Room(42+i, true, true));
        }

        // create reservation and add it to lodge
        res = new Reservation(LocalDate.of(2023, 5, 20), guest, lodge.getRoom(42));
        lodge.getRoom(42).setAvailable(false);
        lodge.getReservations().add(res);
        lodge.addGuest(guest);

        // create composite
        composite = new Composite(guest, res, lodge.getRoom(42), lodge.getRoom(43), lodge);
        composite.addUpdate(LocalDateTime.now(), "composite created");

        try {
            manager = LodgeReservationManager.getInstance();

            success = manager.performAction("RESERVE_ROOM", composite);
            System.out.println("RESERVE ROOM success from perform action: " + success);


            System.out.println("room before update: " + composite.getReservation().getRoom());
            success = manager.performAction("UPDATE_RESERVATION_ROOM", composite);
            System.out.println("UpdateReservation success: " + success +
                    "\nroom after update: " + composite.getReservation().getRoom());

            success = manager.performAction("CANCEL_RESERVATION", composite);
            System.out.println("CANCEL RESERVATION success: " + success);

            success = manager.performAction("CHECK_INVENTORY", composite);
            System.out.println("CHECK_INVENTORY success: " + success);

        } catch (Exception e) {
            System.err.println("Exception from main: " + e.getMessage());
        }

        manager = LodgeReservationManager.getInstance();
        success = manager.performAction("LOGIN_LODGE_GUEST", composite);
        System.out.println("LOGIN success: " + success);
    }
}
