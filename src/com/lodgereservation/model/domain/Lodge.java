

package com.lodgereservation.model.domain;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Lodge {
    static final AtomicLong NEXT_RES_ID = new AtomicLong(1);           // citation: https://stackoverflow.com/questions/8938528/how-do-i-get-a-unique-id-per-object-in-java

    private String lodgeName;
    private String lodgeAddr;                                           //todo Address or ContactInfo object
    private ArrayList<Reservation> reservations;
    private ArrayList<Room> rooms;
    private ArrayList<LodgeGuest> guests;                               /*todo HashSet wrapped in synchronized set https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedSet-java.util.Set-
                                                                         * https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html*/

    public Lodge() {
        lodgeName = "unnamed lodge";
        lodgeAddr = "blank lodge address";
        reservations = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    public Lodge(String ln) {
        lodgeName = ln;
        lodgeAddr = "blank lodge address";
        reservations = new ArrayList<>();
        rooms = new ArrayList<>();
        guests = new ArrayList<>();
    }

    public Lodge(String ln, String addr) {
        lodgeName = ln;
        lodgeAddr = addr;
        reservations = new ArrayList<>();
        rooms = new ArrayList<>();
        guests = new ArrayList<>();
    }

    public void addGuest(String fn, String ln) {
        LodgeGuest temp = new LodgeGuest(fn, ln, this);
        guests.add(temp);
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

    public ArrayList<LodgeGuest> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<LodgeGuest> guests) {
        this.guests = guests;
    }
}
