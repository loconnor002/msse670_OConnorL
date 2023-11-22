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
     * @param composite     holds application-specific domain state
     * @return action       true if the action was successful, false otherwise
     */
    public boolean performAction(String commandString, Composite composite) {
        boolean action = false;
        if (commandString.equals("RESERVE_ROOM")) {
            action = bookReservation(composite);
        }
        else if (commandString.equals("LOGIN_LODGE_GUEST")) {
            action = loginLodgeGuest(composite);
        }
        else if (commandString.equals("CHECK_INVENTORY")) {
            action = checkInventory(composite);
        }
        else if (commandString.equals("UPDATE_RESERVATION_ROOM")) {
            action = updateReservationRoom(composite);
        }
        else if (commandString.equals("CANCEL_RESERVATION")) {
            action = cancelReservation(composite);
        }
        else {
            //System.out.println("INFO:   Add new workflows here using if/else.");
            System.out.println("NO ACTION PERFORMED... LodgeReservationMgr:: commandString: " + commandString);
        }
        return action;
    }

    private boolean updateReservationRoom(Composite composite) {
        boolean isUpdated = false;
        ServiceFactory sf = ServiceFactory.getInstance();
        IReservationService reservationService;

        try {
            reservationService = (IReservationService) sf.getService(IReservationService.NAME);
            isUpdated = reservationService.updateReservationRoom(composite.getLodge(), composite.getReservation(), composite.getNewRoom());
        } catch(ServiceLoadException sle) {
            System.err.println("ServiceLoadException from LodgeReservationManager: " + sle.getMessage());
        } catch (ReservationException re) {
            System.out.println(re.getMessage());
        }
        return isUpdated;
    }


    /**
     * Book a reservation.
     *
     * @param composite the application-specific domain state
     * @return true if the reservation was successful, false otherwise
     * @see "LodgeReservationManager.performAction(String, Composite)"
     */
    private boolean bookReservation(Composite composite) {

        boolean isBooked = false;
        ServiceFactory sf = ServiceFactory.getInstance();
        IReservationService reservationService;

         // Propagate errors up the call stack, to be caught here in the Business Layer.
         // https://docs.oracle.com/javase/tutorial/essential/exceptions/advantages.html
        try {
            reservationService = (IReservationService) sf.getService(IReservationService.NAME);
            isBooked = reservationService.bookReservation(composite);

        } catch (ServiceLoadException sle) {
            System.err.println("ServiceLoadException from LodgeReservationManager: " + sle.getMessage());
        } catch (ReservationException re) {
            System.err.println("ReservationException from LodgeReservationManager: " + re.getMessage());
        }
        return isBooked;
    }

    private boolean cancelReservation(Composite composite) {
        boolean isCancelled = false;
        ServiceFactory sf = ServiceFactory.getInstance();
        IReservationService reservationService;

        try {
            reservationService = (IReservationService) sf.getService(IReservationService.NAME);
            isCancelled = reservationService.cancelReservation(composite);
        } catch (ServiceLoadException sle) {
            System.err.println("ServiceLoadException from LodgeReservationManager: " + sle.getMessage());
        } catch (ReservationException e) {
            System.err.println("ReservationException from LodgeReservationManager: " + e.getMessage());
            //throw new RuntimeException(e);
        }
        return isCancelled;
    }

    /**
     * Log in a lodge guest.
     *
     * @param composite the application-specific domain state
     * @return true if the reservation was successful, false otherwise
     * @see "LodgeReservationManager.performAction(String, Composite)"
     */
    private boolean loginLodgeGuest(Composite composite) {
        boolean isLoggedIn = false;

        ServiceFactory sf = ServiceFactory.getInstance();
        ILoginService iLoginService;

        try {
            iLoginService = (ILoginService) sf.getService(ILoginService.NAME);
            isLoggedIn = iLoginService.authenticateUser(composite, composite.getGuest().getPassword());

        } catch (ServiceLoadException sle) {
            System.err.println("ERROR: LodgeReservationManager:: failed to load Login Service " + sle.getMessage());

        } catch (LoginException e) {
            System.err.println("ERROR: LodgeReservationManager:: login failed " + e.getMessage());
            throw new RuntimeException(e);
        }
        return isLoggedIn;
    }


    /**
     * Log in a lodge guest.
     *
     * @param composite the application-specific domain state
     * @return true if the reservation was successful, false otherwise
     * @see "LodgeReservationManager.performAction(String, Composite)"
     */
    private boolean checkInventory(Composite composite) {
        boolean isChecked = false;

        ServiceFactory sf = ServiceFactory.getInstance();
        IInventoryService iInventoryService;

        try {
            iInventoryService = (IInventoryService) sf.getService(IInventoryService.NAME);
            isChecked = iInventoryService.displayAvailableRooms(composite);

        } catch (ServiceLoadException sle) {
            System.err.println("ERROR: LodgeReservationManager:: failed to load Inventory Service");
        }
        catch (InventoryException ie) {
            System.err.println("ERROR: LodgeReservationManager:: failed to load Reservation Service");
        }
        return isChecked;
    }


}