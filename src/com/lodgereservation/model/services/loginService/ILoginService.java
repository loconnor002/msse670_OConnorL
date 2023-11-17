package com.lodgereservation.model.services.loginService;

import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.services.IService;
import com.lodgereservation.model.services.exception.LoginException;

public interface ILoginService extends IService {

    public final String NAME = "ILoginService";

    boolean authenticateUser(Composite composite, String password) throws LoginException;
    boolean findUser(Composite composite);
}
