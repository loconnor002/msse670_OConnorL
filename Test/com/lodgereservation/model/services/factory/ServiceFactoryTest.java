package com.lodgereservation.model.services.factory;

import com.lodgereservation.model.business.exception.ServiceLoadException;
import com.lodgereservation.model.services.inventory.IInventoryService;
import com.lodgereservation.model.services.inventory.InventoryServiceImpl;
import com.lodgereservation.model.services.loginService.ILoginService;
import com.lodgereservation.model.services.loginService.LoginServiceImpl;
import com.lodgereservation.model.services.reservationService.IReservationService;
import com.lodgereservation.model.services.reservationService.ReservationServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ServiceFactoryTest {
    ServiceFactory serviceFactory;

    @Before
    public void setUp() throws Exception {
        serviceFactory = ServiceFactory.getInstance();
    }


    @Test
    public void testGetLoginService() {
        ILoginService loginService;
        try {
            loginService = (ILoginService) serviceFactory.getService(ILoginService.NAME);
            assert (loginService instanceof LoginServiceImpl);
            System.out.println("testGetLoginService PASSED");
        } catch (ServiceLoadException e) {
            e.printStackTrace();
            fail("ServiceLoadException");
        }
    }


    @Test
    public void testGetReservationService() {
        IReservationService reservationService;
        try {
            reservationService = (IReservationService) serviceFactory.getService(IReservationService.NAME);
            assert (reservationService instanceof ReservationServiceImpl);
            System.out.println("testGetReservationService PASSED");
        } catch (ServiceLoadException e) {
            e.printStackTrace();
            fail("ServiceLoadException");
        }
    }


    @Test
    public void testGetInventoryService() {
        IInventoryService inventoryService;
        try {
            inventoryService = (IInventoryService) serviceFactory.getService(IInventoryService.NAME);
            assert (inventoryService instanceof InventoryServiceImpl);
            System.out.println("testGetInventoryService PASSED");
        } catch (ServiceLoadException e) {
            e.printStackTrace();
            fail("ServiceLoadException");
        }
    }
}
