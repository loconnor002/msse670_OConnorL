package com.lodgereservation.model.domain;

import java.util.Objects;

public class LodgeGuest extends Person {

    private String address;                             //todo Address or ContactInfo object
    //private ArrayList<Reservation> reservations;      //todo database instead?
    Lodge lodge;

    public LodgeGuest()  {
    }

    public LodgeGuest(String fn, String ln, Lodge lodge) {
        super.firstName = fn;
        super.lastName = ln;
        this.lodge = lodge;
        this.address = null;
        //reservations = new ArrayList<>();
    }

    public boolean validate() {
        if (!lodge.getGuests().contains(this)) {
            //todo validate GUEST_ID
            return false;
        }
        else if (this.firstName.isEmpty() || this.lastName.isEmpty()) {
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Lodge guest name: " + this.firstName + " " + this.lastName;
    }

    /**
     * Compare two objects, for use with this.hashCode() and hash-based collections
     * <a href="https://www.geeksforgeeks.org/override-equalsobject-hashcode-method/">...</a>
     *
     * @see #hashCode()
     * @param   obj an object to compare to this LodgeGuest
     * @return  true if objects are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        boolean result;

        if (this == obj) {
            result = true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            result = false;
        } else {
            LodgeGuest lg = (LodgeGuest) obj;
            result = (lg.getFirstName().equals(this.firstName) &&
                    lg.getLastName().equals(this.lastName));
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getFirstName(), this.getLastName());
    }
}
