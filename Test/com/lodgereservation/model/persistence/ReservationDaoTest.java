package com.lodgereservation.model.persistence;

import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.domain.LodgeGuest;
import com.lodgereservation.model.domain.Reservation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReservationDaoTest {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ArrayList<LodgeGuest> records;
    private ReservationDao resDao;
    private LodgeGuest guest;

    @BeforeEach
    void setUp() {
        guest = new LodgeGuest();
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/reservations",
                    "msse670",
                    "p@sswordMSSE670");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from guests");
            records = new ArrayList<>();
            resDao = new ReservationDao();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            connection.close();
            assert(connection.isClosed());
            System.out.println("tear down complete, connection closed");
        } catch (SQLException e) {
            System.err.println("Error closing connection " + e);
            fail("SQLException");
        }
    }

    @Test
    void testGetAll() {
        int size = 0;
        int numResults = 0;
        try {
            records = resDao.getAll();
            /*
            while(resultSet.next()) {
                records.add(new LodgeGuest());
                ctr++;
            }

            statement.executeQuery("select count(uuid) as numResults from guests");
            System.out.println(numResults);*/
            size = records.size();
            assert(size != 0);
            System.out.println("testGetAll PASSED");

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Test
    void testAdd() {
        assert(resDao.add(guest));
        System.out.println("testAdd PASSED");
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}