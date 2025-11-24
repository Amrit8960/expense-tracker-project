package dao;

import java.util.List;

/**
 * Generic DAO interface to handle CRUD operations for any entity.
 */
public interface GenericDAO<T> {
    void insert(T obj);
    List<T> getAll();
}
