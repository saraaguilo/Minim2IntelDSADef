package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.FactorySession;
import edu.upc.dsa.CRUD.Session;
import edu.upc.dsa.exceptions.NonExistentItemException;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;


public class InventoryDAOImpl implements IInventoryDAO {
    final static Logger logger = Logger.getLogger(ItemDAOImpl.class);
    private static InventoryDAOImpl instance;
    public static InventoryDAOImpl getInstance() {
        if (instance==null) instance = new InventoryDAOImpl();
        return instance;
    }
    public Inventory getInventory (String idUser) {
        Session session = null;
        Inventory inventory = null;
        try {
            session = FactorySession.openSession();
            inventory = (Inventory) session.get(Inventory.class, "idUser", idUser);
        }catch (Exception e){
        }
        finally {
            session.close();
        }

        return inventory;
    }

    public List<Inventory> getInventoryitems() {
        Session session = null;
        List<Inventory> inventoryitems=null;
        try {
            session = FactorySession.openSession();
            inventoryitems = session.findAll(Inventory.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return inventoryitems;
    }

}
