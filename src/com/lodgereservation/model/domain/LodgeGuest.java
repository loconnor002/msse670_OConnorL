package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class LodgeGuest extends Person implements Serializable {

    private final UUID GUEST_ID;

    public LodgeGuest()  {
        super();
        GUEST_ID = UUID.randomUUID();
    }

    public LodgeGuest(String firstName, String lastName, String address) {
        super(firstName, lastName, address);
        this.GUEST_ID = UUID.randomUUID();
    }

    public LodgeGuest(String firstName, String lastName, String address, String email) {
        super(firstName, lastName, address, email);
        this.GUEST_ID = UUID.randomUUID();
    }

    /**
     * Validate parameter values, check if guest ID is not null,
     * email contains an '@' character, and names are not blank.
     *
     * @return true if parameters contain appropriate values, false otherwise
     */
    public boolean validate() {
        if (GUEST_ID != null && !firstName.isBlank()
                && !lastName.isBlank() && !address.isBlank()
                && email.contains("@")) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        //todo, StringBuffer?
        return "LodgeGuest{Guest ID=" + GUEST_ID +
                ", FirstName=" + firstName +
                ", LastName=" + lastName +
                ", Address=" + address +
                ", Phone=" + phone +
                ", Email=" + email + "}";
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
        return Objects.hash(GUEST_ID);
    }

    public UUID getID() {
        return GUEST_ID;
    }
}
