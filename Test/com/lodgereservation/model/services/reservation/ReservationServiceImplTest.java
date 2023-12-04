package com.lodgereservation.model.services.reservation;

import com.lodgereservation.model.business.exception.ServiceLoadException;
import com.lodgereservation.model.domain.*;
import com.lodgereservation.model.services.exception.ReservationException;
import com.lodgereservation.model.services.factory.ServiceFactory;
import com.lodgereservation.model.services.reservationService.IReservationService;
import com.lodgereservation.model.services.reservationService.ReservationServiceImpl;
import junit.framework.TestCase;

public class ReservationServiceImplTest extends TestCase {

    private Composite composite;
    private ReservationServiceImpl reservationService;
    private ServiceFactory serviceFactory;
    private LodgeGuest guest;
    private Reservation res;
    private Room room;
    private Lodge lodge;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.guest = new LodgeGuest("Vogon", "Jeltz", "blurble@poetry.com", "Vogoshpere");
        this.res = new Reservation();
        this.room = new Room(8070234);
        this.lodge = new Lodge("Vogon Constructor Fleet", "zz-plural-z-alpha");
        this.composite = new Composite(guest, res, room, lodge);
        try {
            this.serviceFactory = ServiceFactory.getInstance();
            this.reservationService = (ReservationServiceImpl) serviceFactory.getService(IReservationService.NAME);
        } catch (ServiceLoadException sle) {
            System.err.println(sle.getMessage());
        }
    }

    /**
     * Test that a reservation is created.
     */
    public void testBookReservation() {
        boolean booked;
        Reservation res;
        try {
            booked = reservationService.bookReservation(composite);
            assert(booked);
            System.out.println("testBookReservation PASSED");
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Exception");
        }
    }
}
