package com.lodgereservation.model.services.inventory;

import com.lodgereservation.model.domain.*;
import com.lodgereservation.model.services.exception.InventoryException;
import com.lodgereservation.model.services.factory.ServiceFactory;
import junit.framework.TestCase;

public class InventoryServiceTest extends TestCase {

    private Composite composite;
    private InventoryServiceImpl inventoryService;
    private ServiceFactory serviceFactory;
    private LodgeGuest guest;
    private Reservation res;
    private Lodge lodge;
    private Room room;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.guest = new LodgeGuest("Vogon", "Jeltz", "blurble@poetry.com", "Vogoshpere");
        this.res = new Reservation();
        this.room = new Room(8070234);
        this.lodge = new Lodge("Vogon Constructor Fleet", "zz-plural-z-alpha");
        this.composite = new Composite(guest, res, room, lodge);
        room = new Room();
        serviceFactory = ServiceFactory.getInstance();
        inventoryService = (InventoryServiceImpl) serviceFactory.getService(IInventoryService.NAME);
    }

    public void testAddRoomToLodge() {
        boolean authenticated;

        try {
            authenticated = inventoryService.addRoomToLodge(composite);
            assert(authenticated);
            System.out.println("testAddRoomToLodge PASSED");
        }
        catch(InventoryException e) {
            e.printStackTrace();
            fail("InventoryException");
        }
    }
}
