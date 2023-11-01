/**
 * @author Lauren.OConnor
 */
package com.lodgereservation.model.services.reservationService;

import com.lodgereservation.model.domain.Lodge;
import com.lodgereservation.model.domain.Reservation;

import java.util.UUID;

public interface IReservationService {

    Reservation createReservation();
    void listReservations(Lodge lodge);
    boolean updateReservation(Lodge lodge, Reservation res);    //todo pass composite?
    boolean deleteReservation(UUID resID);
}
