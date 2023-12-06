package com.lodgereservation.model.persistence;

import com.lodgereservation.model.domain.LodgeGuest;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This interface specifies CRUD operations upon an object of type T. It uses a
 * Data Access Object design pattern.
 *
 * @author      lauren.oconnor
 * @param <T>   a generic object type
 */
public interface IDao<T> {

    ArrayList<LodgeGuest> getAll();
    boolean add(@NotNull T item) throws SQLException;
    boolean updatePhone(T item) throws SQLException;
    boolean delete(T item);
    ResultSet search(@NotNull T item) throws SQLException;
}
