package com.lodgereservation.model.persistence;

import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.domain.LodgeGuest;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Description: This class acts as a Data Access Object interface between
 *          the LodgeReservationApplication and its underlying MySQL database.
 *          It provides read/write/update functions.
 *
 * @author lauren.oconnor
 */
public class ReservationDao implements Dao<Composite> {

    private ArrayList<LodgeGuest> guestList;
    private Connection connection;
    private ResultSet resultSet;

    public ReservationDao() {
        this.guestList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/reservations",
                    "msse670",
                    "p@sswordMSSE670");
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from guests");

        } catch (SQLException e) {
            System.err.println("From ResDao constructor: " + e);
        }
    }


    /**
     * Obtain a list of all records in the database.
     *
     * @return guestList    ArrayList containing the LodgeGuest objects stored in the database
     */
    @Override
    public ArrayList<LodgeGuest> getAll() {
        LodgeGuest guest;
        try {
            while (resultSet.next()) {
                guest = new LodgeGuest(UUID.fromString(resultSet.getString("uuid")),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"));
                guestList.add(guest);
            }
        } catch (SQLException e) {

            System.err.println("From ReservationDao.getAll()" + e);
        }
        return guestList;
    }


    /**
     * Add a LodgeGuest object to the database with validation.
     *
     * @param composite Composite object containing a LodgeGuest object
     * @return          true if getAll successfully added a valid LodgeGuest to the database, false otherwise.
     */
    @Override
    public boolean add(@NotNull Composite composite) {

        LodgeGuest guest = composite.getGuest();
        boolean success = false;
        PreparedStatement pstmt;
        String firstname, lastname, phone, email, query;

        query = "insert into reservations.guests (uuid, firstname, lastname, email, phone) values (?, ?, ?, ?, ?);";
        firstname = guest.getFirstName();
        lastname = guest.getLastName();
        email = guest.getEmail();
        phone = guest.getPhone();

        if (validateNames(firstname, lastname) && validateEmail(email) && validatePhone(phone)) {

            try {
                //statement = connection.createStatement();   //todo difference between .createStatement() and .prepareStatement?
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, guest.getID().toString());
                pstmt.setString(2, firstname);
                pstmt.setString(3, lastname);
                pstmt.setString(4, email);
                pstmt.setString(5, phone);
                int count = pstmt.executeUpdate();

                success = count > 0;
            } catch (SQLException e) {
                System.err.println("From ResDao.add() " + e);
            }
        }
        return success;
    }


    @Override
    public boolean update(Composite composite) {
        return false;
    }


    /**
     * Delete a specified record from the database.
     *
     * @param composite Composite object containing a LodgeGuest
     * @return          true if delete removed the specified LodgeGuest from the database, false otherwise
     */
    @Override
    public boolean delete(@NotNull Composite composite) {
        int count = 0;
        LodgeGuest guest = composite.getGuest();
        String guestId = guest.getID().toString();
        String query = "select * from reservations.guests where uuid=?;";
        PreparedStatement pstmt;

        assert(guest != null);
        try {
            // if guest is not null, make sure guest exists in the database
            // use parameterized query instead of String query to guard against SQL injection
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, guestId);

            // attempt to find guest in the database
            resultSet = pstmt.executeQuery();

            // guest was found in the database, proceed to delete
            query = "delete from reservations.guests where uuid=?;";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, guestId);
            count = pstmt.executeUpdate();                              // number of rows changed
        } catch (SQLException e) {
            System.err.println(e);
        }
        return (count > 0);
    } //end delete()


    /**
     * Validate an email address.
     *
     * @param email String containing email address
     * @return true if email conforms to regex pattern, false otherwise
     */
    private boolean validateEmail(String email) {
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    /**
     * Validate firstname and lastname.
     *
     * @param firstname String containing a person's first name
     * @param lastname  String containing a person's last name
     * @return          true if names are valid, false otherwise.
     */
    private boolean validateNames(@NotNull String firstname, @NotNull String lastname) {
        String regex = "[a-zA-Z'-]+$";
        boolean validNames = false;

        try {
            validNames = firstname.matches(regex) && lastname.matches(regex);

        } catch (PatternSyntaxException e) {
            System.err.println(e);
        }
        return validNames;
    }


    /**
     * Validate a phone number.
     *
     * @param phone String containing a phone number
     * @return      true if the phone number is valid, false otherwise.
     */
    private boolean validatePhone(@NotNull String phone) {
        String regex = "^[0-9\\-() ]+$";
        boolean validPhone = false;

        try {
            validPhone = phone.matches(regex);

        } catch (PatternSyntaxException e) {
            System.err.println(e);
        }
        return validPhone;
    }
}
