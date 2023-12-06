package com.lodgereservation.model.services.reservationService;

import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.domain.Reservation;
import com.lodgereservation.model.domain.Lodge;
import com.lodgereservation.model.domain.Room;
import com.lodgereservation.model.services.exception.ReservationException;

public class ReservationServiceImpl implements IReservationService {


    @Override
    public boolean bookReservation(Composite composite) {
        boolean success = false;
        Lodge lodge = composite.getLodge();
        Reservation res = composite.getReservation();
        Room room;

        if (composite.getReservation() != null) {

            composite.getLodge().getReservations().add(res);
            room = lodge.getRoom(composite.getRoom().getRoomNum());
            room.setAvailable(false);

            success = true;
        }
        return success;
    }


    /**
     * Cancel a reservation.
     *
     * @param composite     Composite containing the information of the lodge and reservation to be cancelled.
     * @return              true if reservation was cancelled and its room set to available, false otherwise.
     */
    @Override
    public boolean cancelReservation(Composite composite) throws ReservationException {
        boolean isCancelled = false;
        Reservation res = composite.getReservation();

        if (composite.getLodge().getReservations().contains(res)) {
            //System.out.println("Cancelling reservation: " + res);
            composite.getLodge().getReservations().remove(res);
            composite.getLodge().getCancellations().add(res);
            res.getRoom().setAvailable(true);
            isCancelled = !composite.getLodge().getReservations().contains(res);
        }
        else {
            System.out.println("Unable to cancel reservation: " + res);
            throw new ReservationException("Unable to cancel reservation: " + res);
        }
        return isCancelled;
    }


    /**
     * READ operation. Display a list of existing reservations for this lodge.
     *
     * @param composite Composite object holding a Lodge
     */
    @Override
    public boolean listReservations(Composite composite) throws ReservationException {
        System.out.println(composite.getLodge().getLodgeName() + composite.getLodge().getReservations());
        return true;
    }


    /**
     * UPDATE operation. Change the room on an existing reservation.
     *
     * @param composite Composite object containing reservation, room, new room
     * @return          true if update was successful, false otherwise
     */
    @Override
    public boolean updateReservationRoom(Composite composite) throws ReservationException {
        Room newRoom = composite.getNewRoom();
        Lodge lodge = composite.getLodge();
        Reservation res = composite.getReservation();

        if (lodge.getReservations().contains(res)) {
            if (newRoom.getAvailable() && newRoom.getClean()) {
                composite.getLodge().getReservations().remove(res);
                composite.getLodge().getRoom(composite.getRoom().getRoomNum()).setAvailable(true);
                newRoom.setAvailable(false);
                composite.getReservation().setRoom(newRoom);
            }
            else {
                System.out.println("Room " + newRoom.getRoomNum() + " not available");
                throw new ReservationException("Room " + newRoom + " is not available");
            }
        } else {
            System.out.println("Reservation not found, cannot update room for: " + res);
            throw new ReservationException("Unable to update reservation: " + res);
        }
        return composite.getReservation().getRoom().getRoomNum() == newRoom.getRoomNum();
    }


    /**
     *
     * @param composite
     * @return
     * @throws ReservationException
     */
    @Override
    public boolean deleteReservation(Composite composite) throws ReservationException {
        boolean success = false;
        Reservation res = composite.getReservation();
        Lodge lodge = composite.getLodge();
        if (lodge.getReservations().contains(res)) {
            lodge.getReservations().remove(res);
            lodge.getCancellations().add(res);
            success = true;

        } else {
            System.out.println("Reservation not found, cannot delete: " + res);
            throw new ReservationException("Error from ReservationServiceImpl: " + res);
        }
        return success;
    }
}
