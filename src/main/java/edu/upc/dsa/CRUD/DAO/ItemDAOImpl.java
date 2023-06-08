package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.FactorySession;
import edu.upc.dsa.CRUD.Session;
import edu.upc.dsa.exceptions.NonExistentItemException;
import edu.upc.dsa.models.Item;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;


public class ItemDAOImpl implements IItemDAO {
    final static Logger logger = Logger.getLogger(ItemDAOImpl.class);
    public Item getItem(String idItem) throws NonExistentItemException {
        Session session = null;
        Item item = null;
        try {
            session = FactorySession.openSession();
            item = (Item) session.get(Item.class, "idItem", idItem);
        } finally {
            //session.save(item);
            session.close();
        }

        return item;
    }

    public List<Item> getItems() {
        Session session = null;
        List<Item> items=null;
        try {
            session = FactorySession.openSession();
            items = session.findAll(Item.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return items;
    }

}
