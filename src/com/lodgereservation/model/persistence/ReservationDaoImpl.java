package com.lodgereservation.model.persistence;

import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.domain.LodgeGuest;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
@SuppressWarnings("ThrowablePrintedToSystemOut")
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
                        //resultSetGetAll.getLong("passwordHash"));
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
     * @return          true if successfully added a valid LodgeGuest to the database or
     *                  avoided a duplicate entry, false otherwise.
     */
    @Override
    public boolean add(@NotNull Composite composite) throws SQLException {

        LodgeGuest guest = composite.getGuest();
        int count = 0;
        PreparedStatement preppedStatement;
        String firstname, lastname, phone, email, query, password;
        Long passwordHash;
        ResultSet resultSetAdd;

        query = "insert into reservations.guests (uuid, firstname, lastname, email, phone, password, passwordHash) values (?, ?, ?, ?, ?, ?, ?);";
        firstname = guest.getFirstName();
        lastname = guest.getLastName();
        email = guest.getEmail();
        phone = guest.getPhone();
        password = guest.getPassword();
        passwordHash = guest.getPasswordHash();

        //validate guest fields
        if (validateNames(firstname, lastname) && validateEmail(email) && validatePhone(phone)) {
            try {
                resultSetAdd = searchByName(composite);                         // empty if no duplicates found
                if (!resultSetAdd.next()) {
                    // insert guest into database, skipping duplicates
                    // using parameterized query instead of String query to guard against SQL injection (owasp.org)
                    preppedStatement = connection.prepareStatement(query);
                    preppedStatement.setString(1, guest.getID().toString());
                    preppedStatement.setString(2, firstname);
                    preppedStatement.setString(3, lastname);
                    preppedStatement.setString(4, email);
                    preppedStatement.setString(5, phone);
                    preppedStatement.setString(6, password);
                    preppedStatement.setLong(7, passwordHash);
                    count = preppedStatement.executeUpdate();       // counts number of rows changed, zero if none
                }
                else if (resultSetAdd.getString("firstname").equals(guest.getFirstName())) {
                    System.out.println(guest.getFirstName() + " " + guest.getLastName() +
                                        " already exists in the database. Duplicate entry avoided.");
                    count = 1;
                }
                else {
                    System.out.println("branches broken from add...");
                }
            } catch (SQLException e) {
                System.err.println("From ResDao.add() " + e);
                throw new SQLException("from resDao.add() SQL Exception", e);
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
    public boolean updatePhone(@NotNull Composite composite) throws SQLException {
        int count;
        LodgeGuest guest = composite.getGuest();
        ResultSet resultSet5;
        assert guest != null : "Null guest";
        String query2;
        PreparedStatement preppedStatement;

        try {
            resultSet5 = searchByName(composite);
            resultSet5.next();
            //update changed phone number if it is not the same as the existing number
            if (!guest.getPhone().equals(resultSet5.getString("phone"))) {
                query2 = "update reservations.guests set phone=? where lastname=?;";
                preppedStatement = connection.prepareStatement(query2);
                preppedStatement.setString(1, guest.getPhone());
                preppedStatement.setString(2, guest.getLastName());
                count = preppedStatement.executeUpdate();                           //count number of records changed
                composite.addUpdate("updated phone to " + guest.getPhone());
            }
            else {
                System.out.println("New guest phone same as old phone. No update performed.");
                count = 1;
            }

        } catch (SQLException e) {
            throw new SQLException("SQL Exception from  dao.update phone", e);
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
        ResultSet resultSetSearch;
        PreparedStatement pstmt;
        String query = "select * from reservations.guests where uuid=?;";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, composite.getGuest().getID().toString());
            resultSetSearch = pstmt.executeQuery();

        } catch (SQLException e) {
            System.err.println(e);
            throw new SQLException("Exception from ReservationDaoImpl", e);
        }
        return resultSetSearch;
    }


    /**
     * Search for a record by ID number.
     *
     * @param composite     Composite object containing a desired LodgeGuest
     * @return              a ResultSet containing the Lodge guest, if found, otherwise an empty ResultSet
     */
    public ResultSet searchByName(@NotNull Composite composite) throws SQLException {
        ResultSet resultSetSearch2;
        PreparedStatement pstmt;
        String query = "select * from reservations.guests where firstname=? AND lastname=?;";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, composite.getGuest().getFirstName());
            pstmt.setString(2, composite.getGuest().getLastName());
            resultSetSearch2 = pstmt.executeQuery();

        } catch (SQLException e) {
            System.err.println(e);
            throw new SQLException("Error searching by name from ReservationDaoImpl", e);
        }
        return resultSetSearch2;
    }


    public void displayDB() {
        int ctr = 0;
        guestList = this.getAll();
        for (LodgeGuest g : guestList) {
            System.out.println(ctr + " " + g);
            ctr++;
        }
    }


    public ResultSet getResultSet() {
        return this.resultSet;
    }


    public byte[] generateHashedPassword(String password) throws NoSuchAlgorithmException {
        byte[] hashedPsswd;
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            hashedPsswd = md.digest(password.getBytes(StandardCharsets.UTF_8));

        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("Error from reservationDaoImpl: ", e);
        }
        return hashedPsswd;
    }


    /**
     * Salt and hash guests' passwords for secure storage in the database.
     *
     * @return                          true if updates were successful, false otherwise
     * @throws SQLException             propagates exception if sql syntax was incorrect or database error
     * @throws NoSuchAlgorithmException propagates exception if sha512 algorithm not found
     */
    public boolean updatePasswordHashes() throws SQLException, NoSuchAlgorithmException {
        //todo decrypt for login
        boolean success = false;
        ReservationDaoImpl dao;
        ResultSet resultSetUpdate;
        PreparedStatement pstmt;
        byte[] hash;
        String query = "update reservations.guests set passwordHash=? where firstname=?;";
        try {
            dao = new ReservationDaoImpl();
            resultSetUpdate = dao.getResultSet();
            while (resultSetUpdate.next()) {
                hash = generateHashedPassword(resultSetUpdate.getString("password"));
                System.out.println(hash);
                pstmt = connection.prepareStatement(query);
                pstmt.setBytes(1, hash);
                pstmt.setString(2, resultSetUpdate.getString("firstname"));
                pstmt.executeUpdate();
                success = true;
            }
        }catch (SQLException e) {
            throw new SQLException("Error from ReservationDaoImpl", e);
        } catch (NoSuchAlgorithmException nse) {
            throw new NoSuchAlgorithmException("Error from ReservationDaoImpl", nse);
        }
        return success;
    }


    public boolean closeDB() throws SQLException {
        boolean success = false;
        try {
            connection.close();
            success = connection.isClosed();
        } catch (SQLException e) {
            System.err.println(e);
            throw new SQLException("Error closing database from ReservationDaoImpl", e);
        }
        return success;
    }
}
