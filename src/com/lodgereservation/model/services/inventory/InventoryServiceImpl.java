package com.lodgereservation.model.services.inventory;

import com.lodgereservation.model.domain.Room;
import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.services.exception.InventoryException;

import java.util.ArrayList;

public class InventoryServiceImpl implements IInventoryService {


    @Override
    public boolean addRoomToLodge(Composite composite) throws InventoryException {
        Room room = composite.getRoom();
        if (room == null) {
            throw new InventoryException(" room is null");
        } else if (composite.getLodge().getRooms().contains(room)) {
            throw new InventoryException(" room already exists in lodge");
        } else {
            composite.getLodge().getRooms().add(room);
        }
        return composite.getLodge().getRooms().contains(composite.getRoom());
    }


    @Override
    public boolean displayRooms(Composite composite) throws InventoryException {
        ArrayList<Room> rooms;
        int ctr = 0;
        //System.out.println("Lodge inventory for " + composite.getLodge().getLodgeName());
        rooms = composite.getLodge().getRooms();
        for (Room room : rooms) {
            //System.out.println(room);
            ctr++;
        }
        if (ctr == 0) {
            throw new InventoryException("\tNo inventory displayed for: " + composite.getLodge());
        }
        return ctr > 0;
    }

    @Override
    public boolean deleteRoom(Composite composite) throws InventoryException {
        Room room = composite.getRoom();
        if (composite.getLodge().getRooms().contains(room)) {
            composite.getLodge().getRooms().remove(room);
        } else {
            throw new InventoryException(" room does not exist in this lodge. Cannot remove.");
        }
        composite.getLodge().getRooms().remove(room);
        return composite.getLodge().getRooms().contains(room);
    }
}
