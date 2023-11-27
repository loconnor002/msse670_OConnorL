package com.lodgereservation.model.persistence;

import com.lodgereservation.model.domain.LodgeGuest;
import com.lodgereservation.model.domain.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Description: This class acts as a Data Access Object interface between
 *          the LodgeReservationApplication and its underlying MySQL database.
 *          It provides read/write/update functions.
 *
 * @author lauren.oconnor
 */
public class ReservationDao implements Dao<Reservation> {

    private ArrayList<Reservation> reservationList;
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
                System.out.println(resultSet.getString("uuid"));
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

    @Override
    public boolean add(Reservation reservation) {
        boolean success = false;

        LodgeGuest guest = reservation.getGuest();
        System.out.println(guest.getFirstName() + " ID: " + guest.getID());
        try {
            statement = connection.createStatement();
            statement.execute("insert into reservations.guests (uuid, firstname, lastname, email, phone) " +
                    "values ('" + guest.getID() + "', '" + guest.getFirstName() +"', '" +  guest.getLastName() +"', '" +  guest.getEmail() + "', '" + guest.getPhone() + "');");
            success = true;
        } catch (SQLException e) {
            System.err.println("From ResDao.add() " + e);
        }
        return success;
    }

    @Override
    public boolean update(Reservation item) {
        return false;
    }

    @Override
    public boolean delete(Reservation item) {
        return false;
    }
}
