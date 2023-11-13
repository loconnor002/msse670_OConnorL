package com.lodgereservation.model.services.loginService;

import com.lodgereservation.model.domain.ReservationComposite;
import com.lodgereservation.model.services.IService;
import com.lodgereservation.model.services.exception.LoginException;

public interface ILoginService extends IService {

    public final String NAME = "ILoginService";

    boolean authenticateUser(ReservationComposite composite, String password) throws LoginException;
    boolean findUser(ReservationComposite composite);
}
