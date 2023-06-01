package edu.upc.dsa.CRUD.DAO;


import edu.upc.dsa.CRUD.FactorySession;
import edu.upc.dsa.CRUD.Session;
import edu.upc.dsa.exceptions.InsufficientMoneyException;
import edu.upc.dsa.exceptions.NonExistentItemException;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class UserDAOImpl implements IUserDAO {
    final static Logger logger = Logger.getLogger(UserDAOImpl.class);

    public int addUser(String idUser, String name, String surname, String email, String password) {
        Session session = null;
        int employeeID = 0;
        try {
            session = FactorySession.openSession();
            User user = new User(idUser,name, surname, email, password);
            session.save(user);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return employeeID;
    }


    public User getUser(String userId) {
        Session session = null;
        User user = null;
        try {
            session = FactorySession.openSession();
            user = (User)session.get(User.class, "id", userId);
        }
        catch (Exception e) {
            e.printStackTrace();
            // LOG
        }
        finally {
            session.close();
        }

        return user;
    }
    public User getUserByEmail(String email) {
        Session session = null;
        User user = null;
        try {
            session = FactorySession.openSession();
            user = (User)session.get(User.class, "email", email);
        }
        catch (Exception e) {
            e.printStackTrace();
            // LOG
        }
        finally {
            session.close();
        }

        return user;
    }

    public void deleteEmployee(String employeeID) {
        User employee = this.getUser(employeeID);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(User.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

    }
    public List<Item> getItems() {
        Session session = null;
        List<Item> items=null;
        try {
            session = FactorySession.openSession();
            items = session.findAll(User.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return items;
    }

    public List<User> getEmployees() {

        Session session = null;
        List<User> employeeList=null;
        try {
            session = FactorySession.openSession();
            employeeList = session.findAll(User.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return employeeList;
    }
    public List<User> getEmployeeByDept(int deptID) {

        Session session = null;
        List<User> employeeList=null;
        try {
            session = FactorySession.openSession();

            HashMap<String, Integer> params = new HashMap<String, Integer>();
            params.put("deptID", deptID);

            employeeList = session.findAll(User.class, params);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return employeeList;
    }
    public void buyItem(String idItem, String idUser) throws InsufficientMoneyException, NonExistentItemException, UserNotRegisteredException, SQLException {
        Session session = null;
        IItemDAO itemDAO = new ItemDAOImpl();
        Item item = itemDAO.getItem(idItem);
        User user = getUser(idUser);

        try {
            session = FactorySession.openSession();
            user.buyItem(item);
        } catch (InsufficientMoneyException e) {
            logger.warn("Not enough money exception");
            throw new InsufficientMoneyException();
        } finally {
            logger.info("Item bought");
            session.update(user);
            Inventory inventory= new Inventory(idUser,idItem);
            session.save(inventory);
            session.close();
        }
    }
}