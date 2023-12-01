package com.lodgereservation.model.presentation;

import com.lodgereservation.model.domain.*;
import com.lodgereservation.model.persistence.ReservationDao;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static void main(String[] args) {
        boolean success;
        int ctr = 1;
        Composite composite;
        LodgeGuest guest;
        ReservationDao database;
        ArrayList<LodgeGuest> guestList;

        guest = new LodgeGuest("Arthur", "Dent", "earth.man@h2g2.com", "17201111110");
        composite = new Composite();
        composite.setGuest(guest);

        try {
            // Create database
            database = new ReservationDao();
            success = database != null;
            System.out.println("Create database connection: success=" + success);

            // Update database (add a guest)
            success = database.add(composite);
            System.out.println("Update database (insert a new LodgeGuest): success=" + success);

            guest.setPhone("19703333333");
            composite.setGuest(guest);
            success = database.updatePhone(composite);
            System.out.println("Update database (change LodgeGuest phone): success=" + success);

            // Read from database
            guestList = database.getAll();
            success = !guestList.isEmpty();
            System.out.println("Read database: success=" + success + "\n Guests from DB:");
            for (LodgeGuest g : guestList) {
                System.out.println(ctr + " " + g);
                ctr++;
            }

            guestList = database.getAll();
            success = !guestList.isEmpty();

            System.out.println("\n\nRead database: success=" + success + "\n Guests from DB:");
            ctr = 1;
            for (LodgeGuest g : guestList) {
                System.out.println(ctr + " " + g);
                ctr++;
            }
            // Delete from database
            //success = database.delete(composite);
            //System.out.println("Delete from database: success=" + success);

        } catch (NullPointerException e) {
            System.err.println(e);
        } catch (Exception e) {
            System.err.println(e);
        }
        //wk5 instantiate & configure Composite obj, pass it to services, print returned output from methods
        //lodge = new Lodge("Alyeska", "Girdwood");
        /*
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

         */
    }
}
