package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.NonExistentItemException;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;

import java.sql.SQLException;
import java.util.List;
public interface IItemDAO {

    public List<Item> getItems();
    Item getItem(String idItem) throws NonExistentItemException, SQLException;


}
