
package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Credentials;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;

import java.sql.SQLException;
import java.util.List;

public interface GameManager {
    int size();
    User register(User user) throws EmailAlreadyInUseException;
    //User login(String email, String password) throws UserNotRegisteredException, IncorrectPasswordException;
    User login(Credentials credentials) throws UserNotRegisteredException, IncorrectPasswordException;
    List<User> getUsers();
    int UserNumber();
    int ItemNumber();
    int LoggedNumber();
    List<Item> Shop();
    List<Inventory> Inventory();
}
