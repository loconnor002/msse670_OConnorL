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

        assertTrue(authenticated);
        System.out.println("testAuthenticateUser PASSED");
    }
}
