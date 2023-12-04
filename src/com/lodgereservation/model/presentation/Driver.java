package com.lodgereservation.model.presentation;

import com.lodgereservation.model.business.manager.LodgeReservationManager;
import com.lodgereservation.model.domain.*;
import com.lodgereservation.model.persistence.ReservationDaoImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        boolean success;
        int ctr = 1;
        Composite composite;
        Lodge lodge;
        LodgeGuest guest;
        LodgeReservationManager manager;
        Reservation res;
        ReservationDaoImpl database;
        Room room, newRoom;
        ArrayList<LodgeGuest> guestList;

        //instantiate & configure Composite obj, pass it to services, print returned output from methods
        lodge = new Lodge("Alyeska", "Girdwood");
        //guest = new LodgeGuest("Arthur", "Dent", "earth.man@h2g2.com", "17201111110");
        guest = new LodgeGuest("Guard", "Vogon", "blaster@vogons.com", "17205184843");
        guest.setPassword("resistance is useless");

        //add 10 rooms to lodge, (roomNumber, available=true, clean=true)
        for (int i = 0; i < 10; i++) {
            lodge.getRooms().add(new Room(42+i, true, true));
        }

        // create reservation and add it to lodge
        lodge.addGuest(guest);
        res = new Reservation(LocalDate.of(2023, 5, 20), guest, lodge.getRoom(42));
        lodge.getRoom(42).setAvailable(false);
        lodge.getReservations().add(res);

        // create composite
        composite = new Composite(guest, res, lodge.getRoom(42), lodge.getRoom(43), lodge);

        try {
            // Create database
            database = new ReservationDaoImpl();

            // Update database (add a guest)
            success = database.add(composite);
            System.out.println("Update database (insert a new LodgeGuest): success=" + success);

            // Update database (change guest's phone number)
            composite.getGuest().setPhone("19703333333");
            success = database.updatePhone(composite);
            System.out.println("Update database (change LodgeGuest phone): success=" + success);

            // Read from database
            //guestList = database.getAll();
            //success = !guestList.isEmpty();
            System.out.println("Read database: success=" + success + "\n Guests from DB:");
            database.displayDB();

            //Delete from database
            //success = database.delete(composite);
            System.out.println("Delete from database: success=" + success);

            success = database.closeDB();
            System.out.println("database connection closed: " + database.closeDB());

        } catch (Exception e) {
            System.err.println("Exception from Main: " + e.getMessage());
        }

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

            manager = LodgeReservationManager.getInstance();
            System.out.println(composite.getGuest().getFirstName());
            success = manager.performAction("LOGIN_LODGE_GUEST", composite);
            System.out.println("LOGIN success: " + success);

        } catch (Exception e) {
            System.err.println("Exception from Main: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
