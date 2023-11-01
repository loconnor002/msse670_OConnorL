/**
 * @author Lauren.OConnor
 */
package com.lodgereservation.model.services.reservationService;

import com.lodgereservation.model.domain.Reservation;

import java.util.UUID;

public interface IReservationService {

    Reservation createReservation();
    void listReservations();
    boolean updateReservation(Reservation res);
    boolean deleteReservation(UUID resID);
}
