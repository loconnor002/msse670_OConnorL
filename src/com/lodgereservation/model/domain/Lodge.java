package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Lodge implements Serializable {
    private String lodgeName;
    private String lodgeAddr;                                           //todo Address or ContactInfo object
    private ArrayList<Reservation> reservations;
    private ArrayList<Reservation> cancellations;
    private ArrayList<Room> rooms;
    private ArrayList<LodgeGuest> guests;                               /*todo HashSet wrapped in synchronized set https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedSet-java.util.Set-
                                                                         * https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html*/
    public Lodge() {
        reservations = new ArrayList<>();
        cancellations = new ArrayList<>();
        rooms = new ArrayList<>();
        guests = new ArrayList<>();
    }

    public Lodge(String lodgeName) {
        this.lodgeName = lodgeName;
        reservations = new ArrayList<>();
        cancellations = new ArrayList<>();
        rooms = new ArrayList<>();
        guests = new ArrayList<>();                                              //default capacity 15, load factor 0.75
    }

    public Lodge(String ln, String addr) {
        lodgeName = ln;
        lodgeAddr = addr;
        reservations = new ArrayList<>();
        cancellations = new ArrayList<>();
        rooms = new ArrayList<>();
        guests = new ArrayList<>();
    }

    public void addGuest(LodgeGuest guest) {
        guests.add(guest);
    }

    public void addReservation(Reservation res) {
        reservations.add(res);
    }

    public String toString() {
        return "Lodge{lodgeName=" + lodgeName +
                ", lodgeAddr="+ lodgeAddr +
                ", totalReservations=" + reservations.size() +
                ", totalGuests=" + guests.size() +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lodge lodge = (Lodge) o;
        return Objects.equals(lodgeName, lodge.lodgeName) && Objects.equals(lodgeAddr, lodge.lodgeAddr) && Objects.equals(reservations, lodge.reservations) && Objects.equals(rooms, lodge.rooms) && Objects.equals(guests, lodge.guests);
    }

    /**
     * Get room by room number.
     *
     * @param roomNum   int number of desired room
     * @return          the desired room if it exists in the lodge, otherwise a dummy room with number -99
     */
    public Room getRoom(int roomNum) {
        Room room = new Room(-99);
        for (Room elem : rooms) {
            if (elem.getRoomNum() == roomNum) {
                room = elem;
            }
        }
        return room;
    }


    @Override
    public int hashCode() {
        return Objects.hash(lodgeName, lodgeAddr, reservations, rooms, guests);
    }

    public String getLodgeName() {
        return lodgeName;
    }

    public void setLodgeName(String lodgeName) {
        this.lodgeName = lodgeName;
    }

    public String getLodgeAddr() {
        return lodgeAddr;
    }

    public void setLodgeAddr(String lodgeAddr) {
        this.lodgeAddr = lodgeAddr;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public ArrayList<Reservation> getCancellations() {
        return cancellations;
    }

    public void setCancellations(ArrayList<Reservation> cancellations) {
        this.cancellations = cancellations;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<LodgeGuest> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<LodgeGuest> guests) {
        this.guests = guests;
    }
}
