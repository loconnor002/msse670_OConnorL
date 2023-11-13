package com.lodgereservation.model.services.inventory;

import com.lodgereservation.model.domain.ReservationComposite;
import com.lodgereservation.model.domain.Room;
import com.lodgereservation.model.services.IService;
import com.lodgereservation.model.services.exception.InventoryException;


public interface IInventoryService extends IService {

    public final String NAME = "IInventoryService";

    boolean addRoomToLodge(ReservationComposite composite, Room room) throws InventoryException;
    boolean makeRoomAvailable(Room room) throws InventoryException;
    void displayAvailableRooms(ReservationComposite composite) throws InventoryException;
    boolean deleteRoom() throws InventoryException;
}
