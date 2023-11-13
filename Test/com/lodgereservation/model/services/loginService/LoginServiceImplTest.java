package com.lodgereservation.model.services.loginService;

import com.lodgereservation.model.business.exception.ServiceLoadException;
import com.lodgereservation.model.domain.LodgeGuest;
import com.lodgereservation.model.domain.ReservationComposite;
import com.lodgereservation.model.services.exception.LoginException;
import com.lodgereservation.model.services.factory.ServiceFactory;
import junit.framework.TestCase;

public class LoginServiceImplTest extends TestCase {

    private ServiceFactory serviceFactory;
    private LodgeGuest guest;
    private LoginServiceImpl loginService = new LoginServiceImpl();
    private ReservationComposite composite = new ReservationComposite();


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        serviceFactory = ServiceFactory.getInstance();
        guest = new LodgeGuest("Prefect", "Ford", "ford.prefect@h2g2.com", "Somewhere in the vicinity of Betelgeuse");
        composite.setGuest(guest);
    }

    /**
     * Test an authenticated user.
     */
    public void testAuthenticateUser() {
        boolean authenticated;
        ILoginService loginService1;
        try {
            loginService1 = (ILoginService) serviceFactory.getService(ILoginService.NAME);
            authenticated = loginService1.authenticateUser(composite, composite.getGuest().getPassword());

            assert (authenticated);
            System.out.println("testAuthenticateUser PASSED");
        } catch (ServiceLoadException e) {
            e.printStackTrace();
            fail("ServiceLoadException");
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
        boolean authenticated;
        authenticated = loginService.authenticateUser(composite, "bad password");

        assert(!authenticated);
        System.out.println("testIncorrectPassword PASSED");
    }

    /**
     * Test that a known valid user is found.
     */
    public void testFindUser() {
        assert(loginService.findUser(composite));
        System.out.println("testFindUser PASSED");
    }

    /**
     * Test that a known valid user is found.
     */
    public void testFindUserInvalid() {
        ReservationComposite c = new ReservationComposite();
        System.out.println(loginService.findUser(c));
        assert(loginService.findUser(c));
        System.out.println("testFindUserInvalid PASSED");
    }
}
