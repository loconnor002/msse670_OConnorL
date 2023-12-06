package com.lodgereservation.model.services.reservationService;

import com.lodgereservation.model.domain.*;
import com.lodgereservation.model.services.IService;
import com.lodgereservation.model.services.exception.ReservationException;

public interface IReservationService extends IService {

    public final String NAME = "IReservationService";

    //todo convert all params to ReservationComposite?
    boolean bookReservation(Composite composite) throws ReservationException;
    boolean cancelReservation(Composite composite) throws ReservationException;
    boolean updateReservationRoom(Composite composite) throws ReservationException;
    boolean deleteReservation(Composite composite) throws ReservationException;
    boolean listReservations(Composite composite) throws ReservationException;

    }
