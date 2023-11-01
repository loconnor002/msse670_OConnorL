package com.lodgereservation.model.services.loginService;

import com.lodgereservation.model.domain.LodgeGuest;

public class LoginServiceImplementation implements ILoginService {


    @Override
    public boolean authenticateLodgeGuest(LodgeGuest guest) {
        //todo check password? store password somewhere...

        if (findUser().equals("user found/not..."))
            return true;
        return false;
    }

    @Override
    public String findUser() {
        System.out.println("LoginServiceImplementation.findUser() stub");

        return "user found/not...";
    }
}
