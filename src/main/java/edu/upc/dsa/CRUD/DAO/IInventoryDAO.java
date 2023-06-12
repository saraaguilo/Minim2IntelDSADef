package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.NonExistentItemException;
import edu.upc.dsa.exceptions.NotInInventoryException;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;

import java.sql.SQLException;
import java.util.List;

public interface IInventoryDAO {
    public List<Inventory> getInventoryitems();
    public List<Inventory> getInventory(String idUser) throws  SQLException,NonExistentItemException, NotInInventoryException;
}
