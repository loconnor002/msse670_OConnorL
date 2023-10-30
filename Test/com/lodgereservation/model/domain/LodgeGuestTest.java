package com.lodgereservation.model.domain;

import junit.framework.TestCase;

public class LodgeGuestTest extends TestCase {

    /**
     * Test a valid LodgeGuest.
     */
    public void testValidate() {
        LodgeGuest lg = new LodgeGuest("Tricia", "McMillan", "Islington");
        lg.setEmail("trillian@protonmail.com");

        assertTrue(lg.validate());
        System.out.println("testValidate PASSED");
    }

    /**
     * Test a valid LodgeGuest with an invalid email address.
     */
    public void testValidateInvalidEmail() {
        LodgeGuest lg = new LodgeGuest("Tricia", "McMillan", "Islington");
        lg.setEmail("not an email address");

        assertFalse(lg.validate());
        System.out.println("testValidateInvalidEmail PASSED");
    }

    /**
     * Test an invalid LodgeGuest (all attributes empty).
     */
    public void testNotValidate() {
        LodgeGuest lg = new LodgeGuest();

        assertFalse(lg.validate());
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
