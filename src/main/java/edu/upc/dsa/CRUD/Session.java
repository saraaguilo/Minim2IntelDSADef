package edu.upc.dsa.CRUD;

import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.User;

import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity);
    void close();
    Object get(Class theClass, String pk, Object value);

    void delete(Object object);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap<String,String> params);
    List<Object> query(String query, Class theClass, HashMap params);
    List<Object> getList(Class theClass, String key, Object value);
    void update(Class theClass, String SET, String valueSET, String WHERE, String valueWHERE);
    void reupdate(Class theClass, String SET, String valueSET, String WHERE, String valueWHERE, String WHERE2, String valueWHERE2);
}
