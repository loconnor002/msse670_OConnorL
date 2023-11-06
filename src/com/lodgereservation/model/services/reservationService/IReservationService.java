package com.lodgereservation.model.services.reservationService;

import com.lodgereservation.model.domain.*;

public interface IReservationService {

    //todo convert all params to ReservationComposite?
    Reservation createReservation();
    void listReservations(Lodge lodge);
    boolean updateReservationRoom(Lodge lodge, Reservation res, Room room);
    boolean deleteReservation(Lodge lodge, Reservation res);
}
