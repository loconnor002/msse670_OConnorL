package com.lodgereservation.model.domain;

import java.util.ArrayList;

public class LodgeGuest {
    private String firstName;
    private String lastName;
    private ArrayList<Reservation> reservations;

    public LodgeGuest(String fn, String ln) {
        firstName = fn;
        lastName = ln;
        reservations = new ArrayList<>();
    }

    public void setFirstName(String fn) {
        firstName = fn;
    }

    public void setLastName(String ln) {
        lastName = ln;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Lodge guest name: " + firstName + " " + lastName;
    }
}
