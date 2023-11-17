package com.lodgereservation.model.services.reservationService;

import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.domain.Reservation;
import com.lodgereservation.model.domain.Lodge;
import com.lodgereservation.model.domain.Room;
import com.lodgereservation.model.services.exception.ReservationException;

public class ReservationServiceImpl implements IReservationService {
    //todo change params to composite domain objects?


    @Override
    public boolean bookReservation(Composite composite) {
        //todo implement
        System.out.println("ResServiceImpl.bookReservation() stub");
        return false;
    }


    /**
     * CREATE operation. Make a new blank reservation.
     * @return new Reservation object
     */
    @Override
    public Reservation createReservation(Composite composite) throws ReservationException {
        return new Reservation(composite);
    }


    /**
     * READ operation. Display a list of existing reservations for this lodge.
     * @param lodge
     */
    @Override
    public void listReservations(Lodge lodge) {
        System.out.println(lodge.getLodgeName() + lodge.getReservations());
    }


    /**
     * UPDATE uperation. Change the room on an existing reservation.
     *
     * @param lodge
     * @param res
     * @param room
     * @return      true if operation successful, false otherwise
     */
    @Override
    public boolean
    updateReservationRoom(Lodge lodge, Reservation res, Room room) {
        if (res.getID() != null && lodge.getReservations().contains(res)) {
            res.setRoom(room);
            room.setAvailable(false);
            return true;
        }
        System.out.println("Reservation not found, cannot update room for: " + res);
        return false;
    }


    /**
     * DELETE operation. Remove a reservation.
     *
     * @param lodge
     * @param res
     * @return      true if operation successful, false otherwise
     */
    @Override
    public boolean deleteReservation(Lodge lodge, Reservation res) {
        if (res.getID() != null && lodge.getReservations().contains(res)) {
            lodge.getReservations().remove(res);
            return true;
        }
        System.out.println("Reservation not found, cannot delete: " + res);
        return false;
    }
}
