package com.lodgereservation.model.services.reservation;

import com.lodgereservation.model.domain.Reservation;
import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.services.exception.ReservationException;
import com.lodgereservation.model.services.factory.ServiceFactory;
import com.lodgereservation.model.services.reservationService.IReservationService;
import com.lodgereservation.model.services.reservationService.ReservationServiceImpl;
import junit.framework.TestCase;

public class ReservationServiceImplTest extends TestCase {

    private Composite composite;
    private ReservationServiceImpl reservationService;
    private ServiceFactory serviceFactory;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        composite = new Composite();
        serviceFactory = ServiceFactory.getInstance();
        reservationService = (ReservationServiceImpl) serviceFactory.getService(IReservationService.NAME);
    }

    /**
     * Test that a reservation is created.
     */
    public void testCreateReservation() {
        boolean authenticated;
        Reservation res;
        try {
            res = reservationService.createReservation(composite);
            authenticated = res instanceof Reservation;

            assert(authenticated);
            System.out.println("testCreateReservation PASSED");
        }
        catch (ReservationException e) {
            e.printStackTrace();
            fail("ReservationException");
        }
    }
}
