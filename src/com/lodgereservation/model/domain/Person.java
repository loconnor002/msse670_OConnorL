package com.lodgereservation.model.domain;

import java.io.Serializable;

/**
 * Superclass to LodgeGuest and Employee.
 *
 * @author lauren.oconnor
 */
public class Person implements Serializable {

    protected String email;
    protected String firstName;
    protected String lastName;
    protected String phone;
    protected String address;   //todo Address or ContactInfo object
    protected String password;
    protected long passwordHash;

    public Person() {
        firstName = "";
        lastName = "";
        phone = "";
        email = "";
        address = "";
        password = "default password";
        passwordHash = 0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000;
    }

    public Person(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = "default password";
        phone = "";
        email = "";
    }

    public Person(String firstName, String lastName, String email, String phone){
        address = "";
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = "default password";
        this.passwordHash = 0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000;
    }

    /**
     * Validate parameter values, check if guest ID is not null,
     * email contains an '@' character, and names are not blank.
     *
     * @return true if parameters contain appropriate values, false otherwise
     */
    public boolean validate() {
        return firstName.matches("[a-zA-Z'-]{1,30}$") &&
                lastName.matches("[a-zA-Z'-]{1,30}$") &&
                phone.matches("^[0-9]{11}$") &&
                email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() { return this.password; }

    public void setPassword(String pswd) {this.password = pswd;}

    public Long getPasswordHash() {
        return this.passwordHash;
    }
}
