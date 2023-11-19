package com.lodgereservation.model.services.inventory;

import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.domain.Room;
import com.lodgereservation.model.services.IService;
import com.lodgereservation.model.services.exception.InventoryException;


public interface IInventoryService extends IService {

    public final String NAME = "IInventoryService";

    boolean addRoomToLodge(Composite composite, Room room) throws InventoryException;
    boolean makeRoomAvailable(Room room) throws InventoryException;
    boolean displayAvailableRooms(Composite composite) throws InventoryException;
    boolean deleteRoom() throws InventoryException;
}
