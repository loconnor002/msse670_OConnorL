package com.lodgereservation.model.domain;

import java.io.Serializable;

public class Person implements Serializable {

    protected String email;
    protected String firstName;
    protected String lastName;
    protected String phone;
    protected String address;   //todo Address or ContactInfo object
    protected String password;

    public Person() {
        firstName = "";
        lastName = "";
        phone = "";
        email = "";
        address = "";
        password = "default password";
    }

    public Person(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = "default password";
        phone = "";
        email = "";
    }

    public Person(String firstName, String lastName, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = "default password";
        phone = "";
        this.email = email;
    }

    public Person(String firstName, String lastName, String phone, String email, String address, String password) {
        this.address = address;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() { return this.password; }
    public void setPassword(String pswd) {this.password = pswd;}
}
