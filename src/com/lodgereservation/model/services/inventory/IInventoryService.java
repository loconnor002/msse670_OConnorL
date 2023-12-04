package com.lodgereservation.model.services.inventory;

import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.domain.Room;
import com.lodgereservation.model.services.IService;
import com.lodgereservation.model.services.exception.InventoryException;

public interface IInventoryService extends IService {

    public final String NAME = "IInventoryService";

    boolean addRoomToLodge(Composite composite) throws InventoryException;
    boolean displayRooms(Composite composite) throws InventoryException;
    boolean deleteRoom(Composite composite) throws InventoryException;
}
