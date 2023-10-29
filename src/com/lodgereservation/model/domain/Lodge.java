package com.lodgereservation.model.domain;

import java.util.ArrayList;
import java.util.HashSet;

public class Lodge {
    private String lodgeName;
    private String lodgeAddr;                                           //todo Address or ContactInfo object
    private ArrayList<Reservation> reservations;
    private ArrayList<Room> rooms;
    private HashSet<LodgeGuest> guests;                               /*todo HashSet wrapped in synchronized set https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedSet-java.util.Set-
                                                                         * https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html*/

    public Lodge() {
        reservations = new ArrayList<>();
        rooms = new ArrayList<>();
        guests = new HashSet<>();
    }

    public Lodge(String lodgeName) {
        this.lodgeName = lodgeName;
        reservations = new ArrayList<>();
        rooms = new ArrayList<>();
        guests = new HashSet<>();
    }

    public Lodge(String ln, String addr) {
        lodgeName = ln;
        lodgeAddr = addr;
        reservations = new ArrayList<>();
        rooms = new ArrayList<>();
        guests = new HashSet<>();
    }

    public void addGuest(LodgeGuest guest) {
        guests.add(guest);
    }

    public void addGuest(String fn, String ln, String addr) {
        LodgeGuest temp = new LodgeGuest(fn, ln, addr);
        guests.add(temp);
    }

    public void updateRoomAvailability(Room room, boolean available) {
        room.setAvailable(available);
    }

    public String toString() {
        return "Lodge: " + lodgeName + " Guests: " + guests;
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

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public HashSet<LodgeGuest> getGuests() {
        return guests;
    }

    public void setGuests(HashSet<LodgeGuest> guests) {
        this.guests = guests;
    }
}
