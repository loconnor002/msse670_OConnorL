package com.lodgereservation.model.services.loginService;

import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.persistence.ReservationDaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImpl implements ILoginService {

    /**
     * Verify a user's credentials and allow them the appropriate level of access to the system.
     *
     * @param composite a domain object
     * @return          true if person exists in the system, false otherwise
     */
    @Override
    public boolean authenticateUser(Composite composite) throws SQLException {
        //todo store password in database? salted & hashed?
        //todo make login for Person?
        ReservationDaoImpl dao;
        ResultSet resultSet;
        boolean loginSuccess = false;
        try {
            System.out.println(composite.getGuest().getLastName());
            dao = new ReservationDaoImpl();
            resultSet = dao.search(composite);
            while (resultSet.next()) {
                System.out.println("from search: " + resultSet.getString("firstname") + " " + resultSet.getString("password"));
                if (resultSet.getString("password").equals("password123")) {
                    loginSuccess = true;
                }
            }

        } catch (SQLException se) {
            throw new SQLException("ERROR: SQLException from LoginService", se);
        }
        return (loginSuccess);
    }

    /**
     * Locate a user in the database.
     *
     * @param composite a domain object
     * @return          true if the user was found in the system, false otherwise
     */
    @Override
    public boolean findUser(Composite composite) throws SQLException {
        //todo SQL query
        //System.out.println("LoginServiceImplementation.findUser() stub");

        return true;
    }
}
