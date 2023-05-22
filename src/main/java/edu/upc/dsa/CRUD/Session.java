package edu.upc.dsa.CRUD;

import edu.upc.dsa.models.User;

import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity);
    void close();
    Object get(Class theClass, String pk, Object value);
    void update(Object object);
    void delete(Object object);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap<String,String> params);
    List<Object> query(String query, Class theClass, HashMap params);
    // Object getByName(Class theClass, String username);
}
