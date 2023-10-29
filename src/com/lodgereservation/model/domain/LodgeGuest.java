package com.lodgereservation.model.domain;

import java.util.Objects;
import java.util.UUID;

public class LodgeGuest extends Person {

    private final UUID ID;
    public LodgeGuest()  {
        ID = UUID.randomUUID();
    }

    public LodgeGuest(String firstName, String lastName, String address) {
        super(firstName, lastName, address);
        this.ID = UUID.randomUUID();
    }

    public boolean validate() {
        if (ID != null && !firstName.isBlank() && !lastName.isBlank() && !address.isBlank()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
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
            result = (lg.getFirstName().equals(firstName) &&
                    lg.getLastName().equals(lastName));
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public UUID getID() {
        return ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addr) {
        address = addr;
    }
}
