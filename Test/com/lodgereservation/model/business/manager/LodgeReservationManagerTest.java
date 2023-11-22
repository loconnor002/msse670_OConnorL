package com.lodgereservation.model.business.manager;

import com.lodgereservation.model.services.factory.ServiceFactory;
import com.lodgereservation.model.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;


public class LodgeReservationManagerTest {

    private LodgeReservationManager mgr;
    private Composite composite;
    private ServiceFactory sf;
    private LodgeGuest guest;
    private Lodge lodge;
    private Room room;
    private Room newRoom;
    private Reservation res;

    @Before
    public void setUp() throws Exception {
        ManagerSuperType.loadProperties();
        mgr = LodgeReservationManager.getInstance();
        guest = new LodgeGuest("Tricia", "McMillan", "Islington");
        lodge = new Lodge("Kodiak", "Kodiak Island, AK");
        room = new Room(41, true, true);
        newRoom = new Room(40, true, true);
        lodge.getRooms().add(room);
        lodge.getRooms().add(newRoom);
        lodge.getGuests().add(guest);
        res = new Reservation(LocalDate.now(), guest, room);
        lodge.addReservation(res);
        composite = new Composite(guest, res, room, newRoom, lodge);
    }

    @Test
    public void testGetInstance() {
    }

    @Test
    public void testPerformActionLogin() {
        boolean performed = false;
        try {
            performed = mgr.performAction("LOGIN_LODGE_GUEST", composite);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assert(performed);
        System.out.println("testPerformAction PASSED");
    }

    @Test
    public void testPerformActionInventory() {
        boolean performed = false;
        try {
            performed = mgr.performAction("CHECK_INVENTORY", composite);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assert(performed);
        System.out.println("testPerformActionInventory PASSED");
    }

    @Test
    public void testPerformActionUpdateRoom() {
        boolean performed = false;
        try {
            performed = mgr.performAction("UPDATE_RESERVATION_ROOM", composite);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assert(performed);
        System.out.println("testPerformActionUpdateRoom PASSED");
    }
}