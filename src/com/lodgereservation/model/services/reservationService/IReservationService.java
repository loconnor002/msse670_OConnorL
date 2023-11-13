package com.lodgereservation.model.services.reservationService;

import com.lodgereservation.model.domain.*;
import com.lodgereservation.model.services.IService;
import com.lodgereservation.model.services.exception.ReservationException;

public interface IReservationService extends IService {

    public final String NAME = "IReservationService";

    //todo convert all params to ReservationComposite?
    Reservation createReservation() throws ReservationException;
    void listReservations(Lodge lodge);
    boolean updateReservationRoom(Lodge lodge, Reservation res, Room room);
    boolean deleteReservation(Lodge lodge, Reservation res);
}
