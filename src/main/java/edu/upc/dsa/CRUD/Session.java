package edu.upc.dsa.CRUD;

import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity) throws SQLException;
    void close();
    Object get(Class theClass, String pk, Object value);
    void delete(Object object);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap<String,String> params) throws SQLException;
    List<Object> query(String query, Class theClass, HashMap params);
    List<Object> getList(Class theClass, String key, Object value);
    void update(Object object) throws SQLException;
}
