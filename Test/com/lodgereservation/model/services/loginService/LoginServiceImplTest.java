package com.lodgereservation.model.services.loginService;

import com.lodgereservation.model.business.exception.ServiceLoadException;
import com.lodgereservation.model.domain.*;
import com.lodgereservation.model.services.exception.LoginException;
import com.lodgereservation.model.services.factory.ServiceFactory;
import com.lodgereservation.model.services.reservationService.IReservationService;
import junit.framework.TestCase;

import java.sql.SQLException;

public class LoginServiceImplTest extends TestCase {

    private ServiceFactory serviceFactory;
    private LodgeGuest guest;
    private Reservation res;
    private Lodge lodge;
    private Room room;
    private ILoginService loginService;
    private IReservationService resService;
    private Composite composite;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.guest = new LodgeGuest("Vogon", "Jeltz", "blurble@poetry.com", "Vogoshpere");
        guest.setPassword("password123");
        this.res = new Reservation();
        this.room = new Room(8070234);
        this.lodge = new Lodge("Vogon Constructor Fleet", "zz-plural-z-alpha");
        this.composite = new Composite(guest, res, room, lodge);
        try {
            this.serviceFactory = ServiceFactory.getInstance();
            this.loginService = (ILoginService) serviceFactory.getService(ILoginService.NAME);
            this.resService = (IReservationService) serviceFactory.getService(IReservationService.NAME);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Test an authenticated user.
     */
    public void testAuthenticateUser() {
        boolean authenticated;
        try {
            authenticated = loginService.authenticateUser(composite);

            assert (authenticated);
            System.out.println("testAuthenticateUser PASSED");
        }
        catch (SQLException se) {
            se.printStackTrace();
            fail("SQLException");
        }
        catch (LoginException e) {
            e.printStackTrace();
            fail("LoginException");
        }
    }


    /**
     * Test an incorrect password.
     */
    public void testIncorrectPassword() {
        boolean authenticated = true;
        try {
            guest = new LodgeGuest("Guard", "Vogon", "blaster@vogons.com", "17205184843");
            composite.setGuest(guest);
            authenticated = loginService.authenticateUser(composite);
        }
        catch (SQLException se) {
            se.printStackTrace();
            fail("SQLException");
        }
        catch (LoginException e) {
            e.printStackTrace();
            fail("LoginException");
        }
        assert(!authenticated);
        System.out.println("testIncorrectPassword PASSED");
    }
}
