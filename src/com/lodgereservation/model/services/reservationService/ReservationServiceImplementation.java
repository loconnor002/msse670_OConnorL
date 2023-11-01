package com.lodgereservation.model.services.reservationService;

import com.lodgereservation.model.domain.Reservation;
import com.lodgereservation.model.domain.Lodge;

import java.util.UUID;

public class ReservationServiceImplementation implements IReservationService {

    @Override
    public Reservation createReservation() {
        System.out.println("ResServiceImpl.createReservation() stub");
        Reservation tempRes = new Reservation();
        return tempRes;
    }

    @Override
    public void listReservations(Lodge lodge) {
        System.out.println(lodge.getLodgeName() + lodge.getReservations());
    }

    @Override
    public boolean updateReservation(Lodge lodge, Reservation res) {

        System.out.println("ResServiceImpl.updateRes(Res) stub");

        if (res.getID() != null && lodge.getReservations().contains(res)) {
            System.out.println("Reservation found: " + res);
            return true;
        }
        System.out.println("Reservation not found: " + res);
        return false;
    }

    @Override
    public boolean deleteReservation(UUID resID) {
        System.out.println("ResServiceImpl.deleteRes(ID) stub: " + resID);
        return false;
    }
}
