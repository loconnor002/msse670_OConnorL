package com.lodgereservation.model.persistence;

import com.lodgereservation.model.domain.LodgeGuest;
import com.lodgereservation.model.domain.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: This class acts as a Data Access Object interface between
 *          the LodgeReservationApplication and its underlying MySQL database.
 *          It provides read/write/update functions.
 *
 * @author lauren.oconnor
 */
public class ReservationDao implements Dao<LodgeGuest> {

    /* Pattern object creates a regex pattern for validating email addresses
     * used with Matcher object in ReservationDao.validate() to prevent SQL injection
     */
    private final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");

    private ArrayList<LodgeGuest> guestList;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public ReservationDao() {
        this.guestList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/reservations",
                    "msse670",
                    "p@sswordMSSE670");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from guests");
        } catch (SQLException e) {
            System.err.println("From ResDao constructor: " + e);
        }
    }


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

    //todo javadoc
    @Override
    public boolean add(LodgeGuest guest) {
        boolean success = false;
        PreparedStatement ps;
        String fn, ln, email, phone;
        fn = guest.getFirstName();
        ln = guest.getLastName();
        email = guest.getEmail();
        phone = guest.getPhone();
        //validate length of Strings
        boolean validNames, validPhone;

        validNames = (fn.length() + ln.length() < 60) && fn.matches("^[a-zA-Z.\\-' ]") && ln.matches("^[a-zA-Z.\\-' ]");
        validPhone = true;
        assert(validNames && validateEmail(email) && validPhone);
//todo build statement to add guest to database
        try {

            statement = connection.createStatement();   //todo difference between .createStatement() and .prepareStatement?
            ps = connection.prepareStatement("insert into reservations.guests (uuid, firstname, lastname, email, phone) " +
                    "values ('" + guest.getID() + "', '" + guest.getFirstName() + "', '" + guest.getLastName() + "', '" + guest.getEmail() + "', '" + guest.getPhone() + "');");
            ps.execute();
            success = true;
        } catch (SQLException e) {
            System.err.println("From ResDao.add() " + e);
        }
        return success;
    }

    @Override
    public boolean update(LodgeGuest guest) {
        return false;
    }

    @Override
    public boolean delete(LodgeGuest guest) {
        return false;
    }


    /**
     * Validate an email address before adding it to the SQL database,
     * to defend against SQL injection attacks.
     *
     * @param email String containing email address
     * @return true if email conforms to regex pattern, false otherwise
     */
    private boolean validateEmail(String email) {
        Matcher matcher;
        matcher = VALID_EMAIL_REGEX.matcher(email);

        return matcher.matches();
    }
}
