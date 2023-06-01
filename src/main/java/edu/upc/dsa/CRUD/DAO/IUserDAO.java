package edu.upc.dsa.CRUD.DAO;


import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;

import java.util.List;

public interface IUserDAO {

    public int addUser(String name, String surname, String email, String password);
    public User getUser(int userId);
    //public void updateUser(int id, String name, String surname, String email, String password);
    public void deleteEmployee(int employeeID);
    public List<Item> getItems();
    public List <User> getEmployeeByDept(int deptId);

    User getUserByEmail(String email);
}
