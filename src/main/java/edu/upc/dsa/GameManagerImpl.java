package edu.upc.dsa;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.exceptions.EmailAlreadyInUseException;
import edu.upc.dsa.exceptions.IncorrectPasswordException;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);
    private HashMap<String, User> UsersMap;
    protected List<User> users;

    private  GameManagerImpl() {
        this.users = new LinkedList<>();
    }
    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.users.size();
        logger.info("size " + ret);

        return ret;
    }

    @Override
    public User Register(User user) throws EmailAlreadyInUseException {
        logger.info("Adding a user");
        User user1 = UsersMap.get(user.getEmail());
        if (user1 == null)
        {
            this.users.add(user);
            this.UsersMap.put(user.getEmail(), user);
            logger.info("User registered");
            return user;
        } else
        {
            logger.info("This email is already being used");
            throw new EmailAlreadyInUseException();
        }
    }
    public User Login(String email, String password) throws UserNotRegisteredException, IncorrectPasswordException {
        User user1 = UsersMap.get(email);
        if (user1!=null) {
            if (!password.equals(user1.getPassword())) {
                logger.warn("Incorrect password");
                throw new IncorrectPasswordException();
            }
            else {
                logger.warn("User logged in");
                return user1;
            }
        } else
            throw new UserNotRegisteredException();
    }
}