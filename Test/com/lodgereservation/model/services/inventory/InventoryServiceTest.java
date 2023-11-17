package com.lodgereservation.model.services.inventory;

import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.domain.Room;
import com.lodgereservation.model.services.exception.InventoryException;
import com.lodgereservation.model.services.factory.ServiceFactory;
import junit.framework.TestCase;

public class InventoryServiceTest extends TestCase {

    private Composite composite;
    private InventoryServiceImpl inventoryService;
    private ServiceFactory serviceFactory;
    private Room room;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        composite = new Composite();
        room = new Room();
        serviceFactory = ServiceFactory.getInstance();
        inventoryService = (InventoryServiceImpl) serviceFactory.getService(IInventoryService.NAME);
    }

    public void testAddRoomToLodge() {
        boolean authenticated;

        try {
            authenticated = inventoryService.addRoomToLodge(composite, room);
            assert(authenticated);
            System.out.println("testAddRoomToLodge PASSED");
        }
        catch(InventoryException e) {
            e.printStackTrace();
            fail("InventoryException");
        }
    }
}
