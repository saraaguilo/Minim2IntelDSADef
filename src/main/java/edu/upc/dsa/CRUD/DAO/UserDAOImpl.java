package edu.upc.dsa.CRUD.DAO;


import edu.upc.dsa.CRUD.FactorySession;
import edu.upc.dsa.CRUD.Session;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class UserDAOImpl implements IUserDAO {
    final static Logger logger = Logger.getLogger(UserDAOImpl.class);

    public int addUser(String name, String surname, String email, String password) {
        Session session = null;
        int employeeID = 0;
        try {
            session = FactorySession.openSession();
            User user = new User(name, surname, email, password);
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


    public User getUser(int userId) {
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
    /**public void updateEmployee(int employeeID, String name, String surname, String  email, String password) {
        User employee = this.getUser(employeeID);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setEmail(email);
        employee.setPassword(password);

        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(User.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }**/


    public void deleteEmployee(int employeeID) {
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

    public User buyItem (String item, String user){
    Session session = null;
    User user1 = null;
    Item item1 = null;
    boolean inposession = false;

        try {
            session = FactorySession.openSession();
            user1 = (User)session.get(User.class, "NAME: ", user);
            logger.info(user1.getName());
            item1 = (Item) session.get(Item.class, "ITEM: ", item);
            List<Inventory> list = new ArrayList<>();

            if (user1.getMoney()>= item1.getPrice())
            {
                double balance = user1.getMoney()- item1.getPrice();
                session.update(User.class, "MONEY", String.valueOf(balance),"NAME: ",user);
                list = (List<Inventory>)session.getList(Inventory.class, "NAME: ", user);
                int i=0;
                while (i< list.size())
                {
                    if (list.get(i).getItem().equals(item))
                    {
                        inposession = true;
                        int qty = list.get(i).getQuantity() +1;
                        session.reupdate(Inventory.class, "QUANTITY", String.valueOf(qty),"USER: ",user, "ITEM", item);
                    }
                    i++;
                }
                if (inposession == false)
                {
                    session.save(new Inventory());
                    session.reupdate(Inventory.class, "QUANTITY", String.valueOf(1),"USER: ",user, "ITEM: ", item);
                }

                user1= (User)session.get(User.class,"USER", user);

            }
            else
            {
                logger.info("Not enough money to buy item");

            }
        }
        catch (Exception e) {

        }
        finally {
            session.close();
        }
        return user1;
    }




}
