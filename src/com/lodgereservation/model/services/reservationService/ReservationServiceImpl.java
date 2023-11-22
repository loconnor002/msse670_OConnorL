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
        boolean success = false;

        if (composite.getReservation() != null) {
            composite.getLodge().getReservations().add(composite.getReservation());
            success = true;
        }
        return success;
    }


    /**
     * Cancel a reservation.
     *
     * @param composite
     * @return
     */
    @Override
    public boolean cancelReservation(Composite composite) throws ReservationException {
        boolean isCancelled = false;
        Reservation res = composite.getReservation();

        if (res.getID() != null && composite.getLodge().getReservations().contains(res)) {
            System.out.println("Cancelling reservation: " + res);

            isCancelled = composite.getLodge().getReservations().contains(res);
        }
        else {
            System.out.println("Unable to cancel reservation: " + res);
        }
        return isCancelled;
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
     * UPDATE operation. Change the room on an existing reservation.
     *
     * @param lodge
     * @param res
     * @param newRoom
     * @return      true if operation successful, false otherwise
     */
    @Override
    public boolean updateReservationRoom(Lodge lodge, Reservation res, Room newRoom) {
        boolean success = false;
        Room oldRoom;
        if (res.getID() != null && lodge.getReservations().contains(res)) {
            oldRoom = res.getRoom();
            if (newRoom.getAvailable() && newRoom.getClean()) {
                lodge.getReservations().remove(res);
                lodge.getRoom(oldRoom.getRoomNum()).setAvailable(true);
                lodge.getRoom(newRoom.getRoomNum()).setAvailable(false);
                res.setRoom(newRoom);
                lodge.getReservations().add(res);

                success = true;
            }
            else {
                System.out.println("Room " + newRoom.getRoomNum() + " not available");
            }
        }
        System.out.println("Reservation not found, cannot update room for: " + res);
        return success;
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
