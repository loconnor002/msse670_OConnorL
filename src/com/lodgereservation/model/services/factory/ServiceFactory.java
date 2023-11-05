package com.lodgereservation.model.services.factory;

import com.lodgereservation.model.services.loginService.ILoginService;
import com.lodgereservation.model.services.loginService.LoginServiceImplementation;
import com.lodgereservation.model.services.reservationService.IReservationService;
import com.lodgereservation.model.services.reservationService.ReservationServiceImplementation;

public class ServiceFactory {

    public ILoginService getLoginService() {

        return new LoginServiceImplementation();
    }

    public IReservationService getResService() {

        return new ReservationServiceImplementation();
    }
}
