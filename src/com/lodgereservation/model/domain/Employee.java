package com.lodgereservation.model.domain;

import java.util.UUID;

public class Employee extends Person{
    //todo add employee login functionality

    private final UUID EMPLOYEE_ID;

    public Employee() {
        super();
        EMPLOYEE_ID = UUID.randomUUID();
    }

    public Employee(String firstName, String lastName, String address) {
        super(firstName, lastName, address);
        EMPLOYEE_ID = UUID.randomUUID();
    }

    public Employee(String firstName, String lastName, String email, String phone) {
        super(firstName, lastName, email, phone);
        EMPLOYEE_ID = UUID.randomUUID();
    }

    /**
     * Represent this Employee object as a String.
     *
     * @return  String containing the attributes of this Employee object.
     */
    @Override
    public String toString() {
        return "Employee{Employee ID=" + EMPLOYEE_ID +
                ", FirstName=" + firstName +
                ", LastName=" + lastName +
                ", Address=" + address +
                ", Phone=" + phone +
                ", Email=" + email + "}";

    }

    public UUID getID() {
        return EMPLOYEE_ID;
    }
}
