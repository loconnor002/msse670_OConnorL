package com.lodgereservation.model.services;

import com.lodgereservation.model.domain.ReservationComposite;
import com.lodgereservation.model.services.loginService.LoginServiceImplementation;
import junit.framework.TestCase;

public class LoginServiceTest extends TestCase {

    /**
     * Test an authenticated user.
     */
    public void testAuthenticateUser() {
        boolean authenticated;
        LoginServiceImplementation loginService = new LoginServiceImplementation();
        ReservationComposite composite = new ReservationComposite();
        authenticated = loginService.authenticateUser(composite, composite.getGuest().getPassword());

        assert(authenticated);
        System.out.println("testAuthenticateUser PASSED");
    }

    /**
     * Test an incorrect password.
     */
    public void testIncorrectPassword() {
        boolean authenticated;

        LoginServiceImplementation loginService = new LoginServiceImplementation();
        ReservationComposite composite = new ReservationComposite();
        authenticated = loginService.authenticateUser(composite, "bad password");

        assert(!authenticated);
        System.out.println("testIncorrectPassword PASSED");
    }

    public void testFindUser() {
        LoginServiceImplementation loginService = new LoginServiceImplementation();
        ReservationComposite composite = new ReservationComposite();

        assert(loginService.findUser(composite));
        System.out.println("testFindUser PASSED");
    }
}
