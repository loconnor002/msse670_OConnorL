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
 * Description: This class provides an interface (Data Access Object) between
 *          the LodgeReservation application and its underlying MySQL database.
 *          It provides read/write/update functions.
 *          <a href="https://docs.oracle.com/cd/E17952_01/mysql-8.0-en/index.html">Future reference, MySQL</a>
 *
 * @author lauren.oconnor
 */
public class ReservationDaoImpl implements IDao<Composite> {

    private ArrayList<LodgeGuest> guestList;
    private Connection connection;
    private ResultSet resultSet;

    public ReservationDaoImpl() {
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
        Statement statement2;
        ResultSet resultSetGetAll;
        try {
            statement2 = connection.createStatement();
            resultSetGetAll = statement2.executeQuery("select * from guests");

            // create new LodgeGuest from each database record, given column names
            while (resultSetGetAll.next()) {
                guest = new LodgeGuest(UUID.fromString(resultSetGetAll.getString("uuid")),    //cast String value to UUID
                        resultSetGetAll.getString("firstname"),
                        resultSetGetAll.getString("lastname"),
                        resultSetGetAll.getString("email"),
                        resultSetGetAll.getString("phone"));
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
        int count = 0;
        PreparedStatement preppedStatement;
        String firstname, lastname, phone, email, query;
        ResultSet resultSetAdd;

        query = "insert into reservations.guests (uuid, firstname, lastname, email, phone) values (?, ?, ?, ?, ?);";
        firstname = guest.getFirstName();
        lastname = guest.getLastName();
        email = guest.getEmail();
        phone = guest.getPhone();

        //validate guest fields
        if (validateNames(firstname, lastname) && validateEmail(email) && validatePhone(phone)) {
            try {
                resultSetAdd = search(composite);  //empty if no duplicates found
                if (!resultSetAdd.next()) {
                    //insert guest into database
                    // use parameterized query instead of String query to guard against SQL injection (owasp.org)
                    preppedStatement = connection.prepareStatement(query);
                    preppedStatement.setString(1, guest.getID().toString());
                    preppedStatement.setString(2, firstname);
                    preppedStatement.setString(3, lastname);
                    preppedStatement.setString(4, email);
                    preppedStatement.setString(5, phone);
                    count = preppedStatement.executeUpdate();

                }
            } catch (SQLException e) {
                System.err.println("From ResDao.add() " + e);
            }
        }
        return (count > 0);
    }

    /**
     * Update the phone number.
     *
     * @param composite Composite object containing the LodgeGuest whose new phone number
     *                  now differs from their phone number stored in the database.
     * @return          true if phone was updated successfully, false otherwise.
     */
    @Override
    public boolean updatePhone(@NotNull Composite composite) {
        int count = 0;
        LodgeGuest guest = composite.getGuest();
        ResultSet resultSet5;
        assert guest != null : "Null guest";
        String guestID = guest.getID().toString();
        String query2;
        PreparedStatement preppedStatement;

        try {
            resultSet5 = search(composite);//preppedStatement.executeQuery();
            resultSet5.next();

            //update changed phone number if it is not the same as the existing number
            if (!guest.getPhone().equals(resultSet5.getString("phone"))) {
                query2 = "update reservations.guests set phone=? where uuid=?;";
                preppedStatement = connection.prepareStatement(query2);
                preppedStatement.setString(1, guest.getPhone());
                preppedStatement.setString(2, guestID);
                count = preppedStatement.executeUpdate();      //count number of records changed
                composite.addUpdate("updated phone to " + guest.getPhone());
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        return (count > 0);
    } //end update()


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
        String guestID, query;

        assert guest != null : "Guest is null";
        guestID = guest.getID().toString();
        PreparedStatement pstmt;
        ResultSet resultSetDelete;

        try {
            // if guest is not null, make sure guest exists in the database
            resultSetDelete = search(composite);

            if (resultSetDelete.next()) {
                // guest was found in the database, proceed to delete
                query = "delete from reservations.guests where uuid=?;";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, guestID);
                count = pstmt.executeUpdate();                              // number of rows changed
            } else {
                System.out.println(guest.getFirstName() + " not found in the database. Cannot delete.");
            }
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
        //better ways to validate email address:
        //  . test by sending email and not using until valid reply received
        //  . bigger, scarier regex than this:
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
        String regex = "[a-zA-Z'-]{1,30}$";
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
        String regex = "^[0-9\\-() ]{1,14}$";
        boolean validPhone = false;

        try {
            validPhone = phone.matches(regex);

        } catch (PatternSyntaxException e) {
            System.err.println(e);
        }
        return validPhone;
    }


    /**
     * Search for a record by ID number.
     *
     * @param composite     Composite object containing a desired LodgeGuest
     * @return              a ResultSet containing the Lodge guest, if found, otherwise an empty ResultSet
     */
    @Override
    public ResultSet search(@NotNull Composite composite) throws SQLException {
        ResultSet resultSetSearch = null;
        PreparedStatement pstmt;
        String query = "select * from reservations.guests where firstname=?;";  //todo revert to uuid
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, composite.getGuest().getFirstName().toString()); //todo revert to search by uuid (from firstname)
            resultSetSearch = pstmt.executeQuery();
        } catch (SQLException e) {
            System.err.println(e);
            throw new SQLException("Exception from ReservationDaoImpl", e);
        }
        return resultSetSearch;
    }


    public void displayDB() {
        int ctr = 0;
        guestList = this.getAll();
        for (LodgeGuest g : guestList) {
            System.out.println(ctr + " " + g);
            ctr++;
        }
    }

    public boolean closeDB() {
        boolean success = false;
        try {
            connection.close();
            success = connection.isClosed();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return success;
    }
}
