package com.lodgereservation.model.services.factory;

import com.lodgereservation.model.services.loginService.ILoginService;
import com.lodgereservation.model.services.loginService.LoginServiceImplementation;

public class ServiceFactory {

    public ILoginService getLoginService() {

        System.out.println("ServiceFactory.getLoginService() stub");

        return new LoginServiceImplementation();
    }
}
