package com.lodgereservation.model.services.inventory;

import com.lodgereservation.model.domain.Room;
import com.lodgereservation.model.domain.ReservationComposite;
import com.lodgereservation.model.services.exception.InventoryException;

public class InventoryServiceImpl implements IInventoryService {

    @Override
    public boolean addRoomToLodge(ReservationComposite composite, Room room) throws InventoryException {

        if (room == null || composite.getLodge().getRooms().contains(room)) {
            return false;
        }
        composite.getLodge().getRooms().add(room);
        return true;
    }

    @Override
    public boolean makeRoomAvailable(Room room) throws InventoryException {
        if (room.getClean() && room.getAvailable()){
            room.setAvailable(true);
            //todo update within lodge rooms data structure?
            return true;
        }
        return false;
    }

    @Override
    public void displayAvailableRooms(ReservationComposite composite) {
        System.out.println(composite.getLodge().getRooms());
    }

    @Override
    public boolean deleteRoom() throws InventoryException {
        //todo implement delete
        return false;
    }
}
