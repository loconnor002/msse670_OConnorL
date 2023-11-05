package com.lodgereservation.model.services.reservationService;

import com.lodgereservation.model.domain.Reservation;
import com.lodgereservation.model.domain.Lodge;
import com.lodgereservation.model.domain.Room;

public class ReservationServiceImplementation implements IReservationService {

    @Override
    public Reservation createReservation() {
        return new Reservation();
    }

    @Override
    public void listReservations(Lodge lodge) {
        System.out.println(lodge.getLodgeName() + lodge.getReservations());
    }

    @Override
    public boolean
    updateReservationRoom(Lodge lodge, Reservation res, Room room) {

        if (res.getID() != null && lodge.getReservations().contains(res)) {
            res.setRoom(room);
            return true;
        }
        System.out.println("Reservation not found, cannot update room for: " + res);
        return false;
    }

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
