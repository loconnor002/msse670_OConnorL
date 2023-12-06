package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class LodgeGuest extends Person implements Serializable {

    private final UUID GUEST_ID;

    public LodgeGuest(UUID uuid, String firstname, String lastname, String email, String phone, long passwordHash)  {
        super();
        GUEST_ID = uuid;
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.phone = phone;
        this.password = "password123";
        this.passwordHash = passwordHash;
    }

    public LodgeGuest(String firstName, String lastName, String address) {
        super(firstName, lastName, address);
        this.GUEST_ID = UUID.randomUUID();
    }

    public LodgeGuest(String firstName, String lastName, String address, String email) {
        super(firstName, lastName, address, email);
        this.GUEST_ID = UUID.randomUUID();
    }

    public LodgeGuest(UUID id, String firstName, String lastName, String email, String phone) {
        super(firstName, lastName, email, phone);
        assert validate() : "Invalid name, phone, or email";
        this.GUEST_ID = id;
    }


    /**
     * Represent this LodgeGuest object as a String.
     *
     * @return  String containing the attributes of this LodgeGuest object.
     */
    @Override
    public String toString() {
        return "LodgeGuest{Guest ID=" + GUEST_ID +
                ", FirstName=" + firstName +
                ", LastName=" + lastName +
                ", Address=" + address +
                ", Phone=" + phone +
                ", Email=" + email +
                ", PasswordHash=" + String.valueOf(passwordHash) + "}";
    }


    /**
     * Compare two objects, for use with this.hashCode() and hash-based collections
     * <a href="https://www.geeksforgeeks.org/override-equalsobject-hashcode-method/">Future Reference</a>
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
            result = (lg.getID().toString().equals(GUEST_ID.toString()));
        }
        return result;
    }


    /**
     * Generate a hash code for this object's UUID.
     * <a href="https://www.geeksforgeeks.org/override-equalsobject-hashcode-method/">Future Reference</a>
     *
     * @return  integer hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);   //(GUEST_ID);
    }

    public UUID getID() {
        return GUEST_ID;
    }
}
