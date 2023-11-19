package com.lodgereservation.model.business.manager;

import com.lodgereservation.model.business.exception.ServiceLoadException;
import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.services.exception.InventoryException;
import com.lodgereservation.model.services.exception.LoginException;
import com.lodgereservation.model.services.exception.ReservationException;
import com.lodgereservation.model.services.factory.ServiceFactory;
import com.lodgereservation.model.services.inventory.IInventoryService;
import com.lodgereservation.model.services.loginService.ILoginService;
import com.lodgereservation.model.services.reservationService.IReservationService;

public class LodgeReservationManager extends ManagerSuperType {

    //todo - why static?
    private static LodgeReservationManager myInstance;


    private LodgeReservationManager() {
        //keep constructor private to prevent instantiation by outside callers
        //todo implement?
    }

    /**
     * Ensure that only one LodgeReservationManager gets created.
     * todo - why? threads?
     *
     * @return LodgeReservationManager instance
     */
    public static synchronized LodgeReservationManager getInstance() {
        if (myInstance == null) {
            myInstance = new LodgeReservationManager();
        }
        return myInstance;
    }

    /**
     * Facade design pattern, allow all clients of the LodgeReservationManager class
     * to perform certain named actions.
     *
     * @param commandString holds the name of the action to be performed
     * @param composite     holds application-specific domain state //todo understand
     * @return action       true if the action was successful, false otherwise
     */
    public boolean performAction(String commandString, Composite composite) {
        boolean action = false;
        if (commandString.equals("RESERVE_ROOM")) {
            action = bookReservation(IReservationService.NAME, composite);
        }
        else if (commandString.equals("LOGIN_LODGE_GUEST")) {
            action = loginLodgeGuest(ILoginService.NAME, composite);
        }
        else if (commandString.equals("CHECK_INVENTORY")) {
            action = checkInventory(IInventoryService.NAME, composite);
        }
        else {
            System.out.println("INFO:   Add new workflows here using if/else.");
        }
        return action;
    }


    /**
     * Book a reservation.
     *
     * @param commandString the name of the service to be invoked
     * @param composite     the application-specific domain state
     * @return              true if the reservation was successful, false otherwise
     * @see                 "LodgeReservationManager.performAction(String, Composite)"
     */
    private boolean bookReservation(String commandString, Composite composite) {
        // todo, changed to private (notes p. 35) - find caller in sample code?

        boolean isBooked = false;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IReservationService reservationService;

        try {
            reservationService = (IReservationService) serviceFactory.getService(commandString);
            isBooked = reservationService.bookReservation(composite);

        } catch (ServiceLoadException sle) {
            sle.printStackTrace();
            System.out.println("ERROR: LodgeReservationManager:: failed to load Reservation Service");
        }/* catch (ReservationException re) {
            System.out.println("ERROR: LodgeReservationManager:: bookReservation() failed");
            re.printStackTrace();
        }*/
        return isBooked;
    }

    /**
     * Log in a lodge guest.
     *
     * @param commandString the name of the service to be invoked
     * @param composite     the application-specific domain state
     * @return              true if the reservation was successful, false otherwise
     * @see                 "LodgeReservationManager.performAction(String, Composite)"
     */
    private boolean loginLodgeGuest(String commandString, Composite composite) {
        boolean isLoggedIn = false;

        ServiceFactory sf = ServiceFactory.getInstance();
        ILoginService iLoginService;

        try {
            iLoginService = (ILoginService) sf.getService(commandString);
            isLoggedIn = iLoginService.authenticateUser(composite, composite.getGuest().getPassword());

        } catch (ServiceLoadException sle) {
            sle.printStackTrace();
            System.out.println("ERROR: LodgeReservationManager:: failed to load Login Service");
        } catch (LoginException e) {
            System.out.println("ERROR: LodgeReservationManager:: login failed");
            throw new RuntimeException(e);
        }
        return isLoggedIn;
    }


    /**
     * Log in a lodge guest.
     *
     * @param commandString the name of the service to be invoked
     * @param composite     the application-specific domain state
     * @return              true if the reservation was successful, false otherwise
     * @see                 "LodgeReservationManager.performAction(String, Composite)"
     */
    private boolean checkInventory(String commandString, Composite composite) {
        boolean isChecked = false;

        ServiceFactory sf = ServiceFactory.getInstance();
        IInventoryService iInventoryService;

        try {
            iInventoryService = (IInventoryService) sf.getService(commandString);
            isChecked = iInventoryService.displayAvailableRooms(composite);

        } catch (ServiceLoadException sle) {
            sle.printStackTrace();
            System.out.println("ERROR: LodgeReservationManager:: failed to load Inventory Service");
        }
        catch (InventoryException ie) {
            ie.printStackTrace();
            System.out.println("ERROR: LodgeReservationManager:: failed to load Reservation Service");
        }
        return isChecked;
    }
}