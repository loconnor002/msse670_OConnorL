import com.lodgereservation.model.domain.LodgeGuest;
import org.junit.Test;

import static org.junit.Assert.fail;

public class TestLodgeGuest {

    public void printSuccess(boolean condition) {
        if (condition) {
            System.out.println("passed");
        }
        else {
            System.out.println("failed");
        }
    }

    @Test
    public void validate() {
        System.out.println("\nrunning TestLodgeGuest.validate");

        boolean successCondition;
        LodgeGuest guest = new LodgeGuest("firstName", "lastName", "address");

        assert(guest.getFirstName() != null);
        successCondition = guest.validate();
        printSuccess(successCondition);
        if (!successCondition) {
            fail("LodgeGuest(name, lastName) failed");
        }
    }

    @Test
    public void defaultConstructorID(){

        System.out.println("\nrunning TestLodgeGuest.defaultConstructorID()");
        LodgeGuest guest = new LodgeGuest();
        if (guest.getID() != null) {
            printSuccess(guest.getID() != null);
        }
        else {
            fail("lodgeGuest ID is null");
        }
    }

    @Test
    public void overloadedConstructor(){
        System.out.println("\nrunning TestLodgeGuest.overloadedConstructor");
        boolean successCondition;
        String firstName = "Arthur";
        String lastName = "Dent";
        String addr = "Cottington, England";
        System.out.println("\nrunning TestValidate.testOverloadedConstructor()" +
                           "\nExpected: " + firstName + " " + lastName);
        LodgeGuest guest = new LodgeGuest(firstName, lastName, addr);

        successCondition = guest.getFirstName().equals(firstName) &&
                           guest.getLastName().equals(lastName);
        if (successCondition) {
            System.out.println("Actual: " + firstName + " " + lastName);
        }
        else {
            fail("actual name values don't match expected\n");
        }
        printSuccess(successCondition);
    }
}
