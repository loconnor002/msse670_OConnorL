package com.lodgereservation.model.services.inventory;

import com.lodgereservation.model.domain.Room;
import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.services.exception.InventoryException;

import java.util.ArrayList;

public class InventoryServiceImpl implements IInventoryService {

    @Override
    public boolean addRoomToLodge(Composite composite, Room room) throws InventoryException {

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
    public void displayAvailableRooms(Composite composite) {
        ArrayList<Room> rooms;
        rooms = composite.getLodge().getRooms();
        for (Room room : rooms) {
            if (room.getAvailable()) {
                System.out.println(room);
            }
            else {
                System.out.println("not available");
            }
        }
    }

    @Override
    public boolean deleteRoom() throws InventoryException {
        //todo implement delete
        return false;
    }
}
