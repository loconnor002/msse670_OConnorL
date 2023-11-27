package com.lodgereservation.model.persistence;

import com.lodgereservation.model.domain.LodgeGuest;

import java.util.ArrayList;

/**
 * This interface specifies CRUD operations upon an object of type T. It uses a
 * Data Access Object design pattern.
 *
 * @author      lauren.oconnor
 * @param <T>   a generic object type
 */
public interface Dao<T> {
    ArrayList<LodgeGuest> getAll();
    boolean add(T item);
    boolean update(T item);
    boolean delete(T item);
}
