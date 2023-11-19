package com.lodgereservation.model.business.manager;

import com.lodgereservation.model.business.exception.ServiceLoadException;
import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.services.exception.ReservationException;
import com.lodgereservation.model.services.factory.ServiceFactory;
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
        if (commandString.equals("BOOK_RESERVATION")) {
            action = bookReservation(IReservationService.NAME, composite);
        }
        else {
            System.out.println("INFO:   Add new workflows here using if/else.");
        }
        return action;
    }


    // todo, changed to private (notes p. 35) - find caller in sample code
    //@see                 "LodgeReservationManager.performAction(String, Composite)"
    private boolean bookReservation(String commandString, Composite composite) {
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
}