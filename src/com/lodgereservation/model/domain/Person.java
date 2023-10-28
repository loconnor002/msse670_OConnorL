package com.lodgereservation.model.domain;

public abstract class Person {

    protected String firstName;
    protected String lastName;

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

}
