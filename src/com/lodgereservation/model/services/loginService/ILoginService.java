package com.lodgereservation.model.services.loginService;

import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.services.IService;
import com.lodgereservation.model.services.exception.LoginException;

import java.sql.SQLException;

public interface ILoginService extends IService {

    public final String NAME = "ILoginService";

    boolean authenticateUser(Composite composite) throws LoginException, SQLException;
}
