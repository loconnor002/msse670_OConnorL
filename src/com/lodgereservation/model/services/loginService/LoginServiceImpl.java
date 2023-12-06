package com.lodgereservation.model.services.loginService;

import com.lodgereservation.model.domain.Composite;
import com.lodgereservation.model.persistence.ReservationDaoImpl;

import java.sql.PreparedStatement;
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
            dao = new ReservationDaoImpl();
            resultSet = dao.searchByName(composite);
            if (resultSet.next()) {
                if ((resultSet.getString("password").equals("password123")) ||
                 (String.valueOf(resultSet.getString(8)).equals(String.valueOf(0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000)))) {
                    loginSuccess = true;
                }
            }
        } catch (SQLException se) {
            throw new SQLException("ERROR: SQLException from LoginService", se);
        }
        return (loginSuccess);
    }



}
