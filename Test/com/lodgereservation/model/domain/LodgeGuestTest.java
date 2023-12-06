package com.lodgereservation.model.domain;

import junit.framework.TestCase;

public class LodgeGuestTest extends TestCase {

    /**
     * Test a valid LodgeGuest.
     */
    public void testValidate() {
        LodgeGuest lg = new LodgeGuest("Tricia", "McMillan", "trillian@protonmail.com", "17209475844");
        lg.setEmail("trillian@protonmail.com");

        assert(lg.validate());
        System.out.println("testValidate PASSED");
    }

    /**
     * Test a valid LodgeGuest with an invalid email address.
     */
    public void testValidateInvalidEmail() {
        LodgeGuest lg = new LodgeGuest("Tricia", "McMillan", "Islington");
        lg.setEmail("not an email address");

        assert(!lg.validate());
        System.out.println("testValidateInvalidEmail PASSED");
    }

    /**
     * Test an invalid LodgeGuest (all attributes empty).
     */
    public void testNotValidate() {
        LodgeGuest lg = new LodgeGuest("nope", "nope", "nope", "nope");
        assert(!lg.validate());
        System.out.println("testNotValidate PASSED");
    }

    /**
     * Test the setAddress() method.
     */
    public void testSetAddress() {
        String str;
        str = "Magrathea";
        LodgeGuest lg = new LodgeGuest("Tricia", "McMillan", "Islington");
        lg.setAddress(str);

        assertEquals(str, lg.getAddress());
        System.out.println("testSetAddress PASSED");

    }
}
