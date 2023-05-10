package edu.upc.dsa;

import edu.upc.dsa.exceptions.EmailAlreadyInUseException;
import edu.upc.dsa.exceptions.IncorrectPasswordException;
import edu.upc.dsa.models.Credentials;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import edu.upc.dsa.exceptions.UserNotRegisteredException;

import java.util.List;

public interface GameManager {
    int size();
    User register(User user) throws EmailAlreadyInUseException;
    User login(Credentials credentials) throws UserNotRegisteredException, IncorrectPasswordException;
    List<User> getUsers();
    int UserNumber();
    int ItemNumber();
    int LoggedNumber();
    List<Item> Shop();
}
