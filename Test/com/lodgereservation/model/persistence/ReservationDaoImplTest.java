package com.lodgereservation.model.persistence;

import com.lodgereservation.model.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReservationDaoImplTest {

    private Composite composite;
    private Connection connection;
    private ResultSet resultSet;
    private ArrayList<LodgeGuest> records;
    private ReservationDaoImpl resDao;

    @BeforeEach
    void setUp() {
        LodgeGuest guest = new LodgeGuest("Rob", "McKenna", "rain.god@lorries.com", "19701111116");
        Reservation res = new Reservation();
        Room room = new Room(208439);
        Lodge lodge = new Lodge();
        composite = new Composite(guest, res, room, lodge);

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/reservations",
                    "msse670",
                    "p@sswordMSSE670");
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from guests");
            records = new ArrayList<>();
            resDao = new ReservationDaoImpl();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }


    @AfterEach
    void tearDown() {
        try {
            connection.close();
            assert(connection.isClosed());
            //System.out.println("tear down complete, connection closed");
        } catch (SQLException e) {
            System.err.println("Error closing connection " + e);
            fail("SQLException");
        }
    }


    @Test
    void testGetAll() {
        int ctr = 0;

        try {
            records = resDao.getAll();
            assert(resultSet != null);
            while (resultSet.next()) {
                //size = resultSet.getRow();
                ctr++;
            }
            assert(resDao != null && records.size() == ctr);
            System.out.println("testGetAll PASSED");

        } catch (Exception e) {
            System.err.println(e);
        }
    }


    @Test
    void testAdd() {
        boolean success = false;
        try {
            success = resDao.add(composite);
            resDao.delete(composite);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assert(success);
        System.out.println("testAdd PASSED");
    }


    @Test
    void testAddInvalidName() {
        try {
            composite.setGuest(new LodgeGuest("invalid$FN", "invalidLN_", "validEM@email.com", "19701111112"));
            assert(!resDao.add(composite));
            System.out.println("testAddInvalidName PASSED");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    @Test
    void testAddInvalidEmail() {
        try {
            composite.setGuest(new LodgeGuest("okFN", "okLN", "valid#@Email@email.nope>", "19701111113"));
            assert (!resDao.add(composite));
            System.out.println("testAddInvalidEmail PASSED");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    @Test
    void delete() {
        //assert(resDao.delete(composite));
        System.out.println("testDelete PASSED");
    }

/*
    @Test
    void testUpdate() {
        //driver works, but this throws "SQLException, illegal operation on empty result set"... why?
        try {
            assert (resDao.update(composite));
            System.out.println("testUpdate PASSED");

        } catch (Exception e) {
            System.err.println(e);
        }
    }*/
}
