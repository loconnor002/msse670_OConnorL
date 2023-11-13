package com.lodgereservation.model.services.loginService;

import com.lodgereservation.model.business.exception.ServiceLoadException;
import com.lodgereservation.model.domain.Lodge;
import com.lodgereservation.model.domain.LodgeGuest;
import com.lodgereservation.model.domain.ReservationComposite;
import com.lodgereservation.model.services.exception.LoginException;
import com.lodgereservation.model.services.factory.ServiceFactory;
import com.lodgereservation.model.services.loginService.LoginServiceImpl;
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
        guest = new LodgeGuest("Prefect", "Ford", "ford.prefect@h2g2.com", "Somewhere in the vicinity of Betelgeuse.");
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
            //LoginServiceImpl loginService = new LoginServiceImpl();
            //ReservationComposite composite = new ReservationComposite();
            authenticated = loginService1.authenticateUser(composite, composite.getGuest().getPassword());
            if(loginService1.findUser(composite))
                System.out.println(composite.getGuest());
            assert (authenticated);
            System.out.println("testAuthenticateUser PASSED");
        } catch (ServiceLoadException e) {
            e.printStackTrace();
            fail("ServiceLoadException");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }/*
        catch (LoginException e) {
            e.printStackTrace();
            fail("LoginException");
        }*/


        try {
            LoginServiceImpl loginServiceImpl;
            loginServiceImpl = (LoginServiceImpl) serviceFactory.getService(ILoginService.NAME);
            assert (loginServiceImpl.authenticateUser(composite, composite.getGuest().getPassword()));
            System.out.println("testAuthenticateUser PASSED");
        } catch (ServiceLoadException e) {
            e.printStackTrace();
            fail("ServiceLoadException");
        } catch (Exception e) {//LoginException e) {
            e.printStackTrace();
            fail("LoginException");
        }
    }


    /**
     * Test an incorrect password.
     */
    public void testIncorrectPassword() {
        boolean authenticated;

        LoginServiceImpl loginService = new LoginServiceImpl();
        //composite = new ReservationComposite();
        authenticated = loginService.authenticateUser(composite, "bad password");

        assert(!authenticated);
        System.out.println("testIncorrectPassword PASSED");
    }

    public void testFindUser() {
        //LoginServiceImpl loginService = new LoginServiceImpl();
        composite = new ReservationComposite();

        assert(loginService.findUser(composite));
        System.out.println("testFindUser PASSED");
    }
}
