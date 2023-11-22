package com.lodgereservation.model.presentation;

import com.lodgereservation.model.business.exception.*;
import com.lodgereservation.model.business.manager.LodgeReservationManager;
import com.lodgereservation.model.domain.*;
import com.lodgereservation.model.services.factory.*;
import com.lodgereservation.model.services.inventory.*;
import com.lodgereservation.model.services.loginService.*;
import com.lodgereservation.model.services.reservationService.*;
import com.lodgereservation.model.services.exception.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Driver {
    public static void main(String[] args) {
        boolean authenticated;
        Lodge lodge;
        LodgeGuest guest;
        Room room, room2;
        Reservation res, res2;
        String message;
        InventoryServiceImpl inventoryService;
        LoginServiceImpl loginService;
        ReservationServiceImpl resService;
        LodgeReservationManager manager;
        Composite composite;
        ServiceFactory serviceFactory;

        String password = "default password";   //todo remove wk4
        boolean result;                         //todo remove wk4
        boolean success;                        //todo remove wk4

        //wk5 instantiate & configure Composite obj, pass it to services, print returned output from methods
        guest = new LodgeGuest("Arthur", "Dent", "Cottington");

        //room = new Room(42);

        lodge = new Lodge("Alyeska", "Girdwood");
        //lodge.getRooms().add(room);
        for (int i = 0; i < 10; i++) {
            lodge.getRooms().add(new Room(42+i));
        }
        res = new Reservation(LocalDate.of(2023, 05, 20), guest, lodge.getRoom(42));
        lodge.getReservations().add(res);
        lodge.addGuest(guest);
        System.out.println(lodge.getReservations());

        composite = new Composite(guest, res, lodge);
        composite.addUpdate(LocalDateTime.now(), "composite created");
        serviceFactory = ServiceFactory.getInstance();

        try {
            resService = (ReservationServiceImpl) serviceFactory.getService(IReservationService.NAME);

            //res = resService.createReservation(composite);
            //res.setGuest(guest);
            //res.setRoom(new Room(44));
            //lodge.addReservation(res);

            manager = LodgeReservationManager.getInstance();
            success = manager.performAction("RESERVE_ROOM", composite);  //todo call resService or resMgr??
            //resService.listReservations(lodge);
            System.out.println("RESERVE ROOM success from perform action: " + success);

            //composite = new Composite(guest, res, res.getRoom(), new Room(43), lodge);
            success = manager.performAction("UPDATE_RESERVATION_ROOM", composite);
            System.out.println("UpdateReservation success: " + success +
                    "\nroom after update: " + composite.getReservation().getRoom());

            success = manager.performAction("CANCEL_RESERVATION", composite);
            System.out.println("CANCEL RESERVATION success: " + success);

            success = manager.performAction("CHECK_INVENTORY", composite);
            System.out.println("CHECK_INVENTORY success: " + success);

        } catch (ServiceLoadException e) {
            System.err.println("ServiceLoadException from main: " + e.getMessage());
        }

        manager = LodgeReservationManager.getInstance();
        success = manager.performAction("LOGIN_LODGE_GUEST", composite);
        System.out.println("Login success: " + success);


/*
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
        room.setAvailable(false);
        res.setDate(LocalDate.now());

        // display reservation
        //System.out.println(res);

        // store reservation and guest with the lodge
        lodge.addReservation(res);
        lodge.addGuest(guest);
        lodge.getRooms().add(room);
        lodge.getRooms().add(new Room(1, true));
        // instantiate, initialize, and display ReservationComposite object
        composite = new Composite(guest, res, room, lodge);
        composite.addUpdate(LocalDateTime.now(), "composite created");
        //System.out.println(composite.getLodge().getRooms());

        //System.out.println(composite);

        serviceFactory = ServiceFactory.getInstance();
        guest = new LodgeGuest("Ford", "Prefect", "ford.prefect@h2g2.com", "Somewhere in the vicinity of Betelgeuse");
        composite = new Composite(guest, res, room, lodge);
        composite.addUpdate(LocalDateTime.now(), "added reservation " + res.getID());

        // Demonstrate serviceFactory get reservation service
        try {
            resService = (ReservationServiceImpl) serviceFactory.getService(IReservationService.NAME);
            res = resService.createReservation(composite);
            res.setGuest(guest);
            res.setRoom(new Room(44));
            lodge.addGuest(guest);
            lodge.addReservation(res);
            resService.listReservations(lodge);

        } catch (ServiceLoadException | ReservationException e) {
            e.printStackTrace();
        }

        try {
            manager = LodgeReservationManager.getInstance();
            success = manager.performAction("RESERVE_ROOM", composite);
            //System.out.println("ReserveRoom success: " + success);

            success = manager.performAction("LOGIN_LODGE_GUEST", composite);
            //System.out.println("LoginGuest success: " + success);

            success = manager.performAction("CHECK_INVENTORY", composite);
            //System.out.println("CheckInventory success: " + success);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Demonstrate serviceFactory get login service
        try {
            loginService = (LoginServiceImpl) serviceFactory.getService(ILoginService.NAME);
            authenticated = loginService.authenticateUser(composite, composite.getGuest().getPassword());
            if (authenticated) {
                System.out.print("authenticated: ");
            }
            System.out.println(composite.getGuest() + " pswd: " + composite.getGuest().getPassword());

        } catch (ServiceLoadException e) {
            e.printStackTrace();
        }

        // Demonstrate service factory get inventory service
        try {
            inventoryService = (InventoryServiceImpl) serviceFactory.getService(IInventoryService.NAME);
            inventoryService.displayAvailableRooms(composite);
            inventoryService.addRoomToLodge(composite, new Room(45, true, true));

            inventoryService.displayAvailableRooms(composite);

        } catch (ServiceLoadException | InventoryException e) {
            e.printStackTrace();
        }
*/
        // create new service factory
        // todo remove serviceFactory = new ServiceFactory();

        //Demonstrate CRUD operations from services layer (will be replaced by presentation layer in the coming weeks)
        //LoginService
        /*todo remove result = serviceFactory.getLoginService().findUser(composite);
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


        manager = LodgeReservationManager.getInstance();
        success = manager.performAction("BOOK_RESERVATION", composite);
        System.out.println("BookReservation success: " + success);

         */
    }
}