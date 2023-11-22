package com.lodgereservation.model.services.reservationService;

import com.lodgereservation.model.domain.*;
import com.lodgereservation.model.services.IService;
import com.lodgereservation.model.services.exception.ReservationException;

public interface IReservationService extends IService {

    public final String NAME = "IReservationService";

    //todo convert all params to ReservationComposite?
    boolean bookReservation(Composite composite) throws ReservationException;
    boolean cancelReservation(Composite composite) throws ReservationException;
    Reservation createReservation(Composite composite) throws ReservationException;
    void listReservations(Lodge lodge) throws ReservationException;
    boolean updateReservationRoom(Lodge lodge, Reservation res, Room room) throws ReservationException;
    boolean deleteReservation(Lodge lodge, Reservation res) throws ReservationException;
}
