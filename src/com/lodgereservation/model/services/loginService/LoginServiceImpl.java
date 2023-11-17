package com.lodgereservation.model.services.loginService;

import com.lodgereservation.model.domain.Composite;

public class LoginServiceImpl implements ILoginService {

    /**
     * Verify a user's credentials and give them the appropriate level of access to the system.
     *
     * @param composite a domain object
     * @return          true if person exists in the system, false otherwise
     */
    @Override
    public boolean authenticateUser(Composite composite, String password) {
        //todo implement password check. Store password in database? salted & hashed?

        if (findUser(composite) && password.equals(composite.getGuest().getPassword()))
            return true;

        return false;
    }

    /**
     * Locate a user in the (fake) database.
     *
     * @param composite a domain object
     * @return          true if the user was found in the system, false otherwise
     */
    @Override
    public boolean findUser(Composite composite) {
        //todo SQL query
        //System.out.println("LoginServiceImplementation.findUser() stub");

        return true;
    }
}
