package edu.upc.dsa.CRUD.DAO;


import edu.upc.dsa.exceptions.InsufficientMoneyException;
import edu.upc.dsa.exceptions.NonExistentItemException;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {

    public int addUser(String idUser,String name, String surname, String email, String password);
    public User getUser(String userId) throws UserNotRegisteredException;
    public void deleteEmployee(String employeeID);
    public List<Item> getItems();
    public List <User> getEmployeeByDept(int deptId);
    public void buyItem(String idItem, String idUser) throws InsufficientMoneyException, NonExistentItemException, SQLException;
    User getUserByEmail(String email);
}
