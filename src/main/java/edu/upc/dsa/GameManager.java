package edu.upc.dsa;

import edu.upc.dsa.exceptions.EmailAlreadyInUseException;
import edu.upc.dsa.exceptions.IncorrectPasswordException;
import edu.upc.dsa.models.User;
import edu.upc.dsa.exceptions.UserNotRegisteredException;

import java.util.List;

public interface GameManager {
    public int size();
    User Register(User user) throws EmailAlreadyInUseException;
    User Login(String email, String password) throws UserNotRegisteredException, IncorrectPasswordException;
    public List<User> getUsers();
}
