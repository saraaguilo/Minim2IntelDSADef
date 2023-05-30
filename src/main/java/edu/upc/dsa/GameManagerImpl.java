package edu.upc.dsa;


import edu.upc.dsa.CRUD.DAO.IItemDAO;
import edu.upc.dsa.CRUD.DAO.IUserDAO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import edu.upc.dsa.CRUD.DAO.ItemDAOImpl;
import edu.upc.dsa.CRUD.DAO.UserDAOImpl;
import edu.upc.dsa.exceptions.EmailAlreadyInUseException;
import edu.upc.dsa.exceptions.IncorrectPasswordException;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.Credentials;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);
    private HashMap<String, User> usersMap;
    protected List<User> users;
    protected List<Item> items;
    protected List<User> logged;
    private HashMap<String, User> UsersMap;
    public List<User> getUsers(){
        return users;
    }

    public GameManagerImpl() {
        this.users = new LinkedList<>();
        this.logged = new LinkedList<>();
        this.items = new LinkedList<>();
        usersMap = new HashMap<>();
        UsersMap = new HashMap<String, User>();
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
    public User register(User user) throws EmailAlreadyInUseException {
        User user1 = UsersMap.get(user.getEmail());
        if (user1 == null)
        {
            String name= user.getName();
            String surname= user.getSurname();
            String email= user.getEmail();
            String password= user.getPassword();
            IUserDAO userDAO = new UserDAOImpl();
            userDAO.addUser(name, surname,email,password );
            //this.users.add(user);
            //this.UsersMap.put(user.getEmail(), user);
            logger.info("User registered");
            return user;
        } else
        {
            logger.info("This email is already being used");
            throw new EmailAlreadyInUseException();
        }
    }
    @Override
    public User login(Credentials credentials) throws UserNotRegisteredException, IncorrectPasswordException {
        try {
            IUserDAO userDAO = new UserDAOImpl();
            HashMap<String, String> credentialsHash = new HashMap<>();
            credentialsHash.put("email", credentials.getEmail());
            credentialsHash.put("password", credentials.getPassword());
            User user1 = userDAO.getUserByEmail(credentials.getEmail());
            logger.info(user1.getEmail());
            logger.info(user1.getPassword());
            if (user1.getPassword().equals(credentials.getPassword())) {
                logger.info("Succesful login " + credentials.getEmail());
                return user1;
            } else if (user1.getEmail() == null) {
                logger.info("User not registered");
                throw new UserNotRegisteredException();
            }
        } catch (Exception e) {
            logger.info("Something went wrong");
        }

        logger.warn("Incorrect user name or password");
        throw new IncorrectPasswordException();
    }

    public List<Item> Shop ()
    {
        //items.add (new Item("Potion","Recover 50 health points",15));
        //items.add(new Item("Sword","Increase damage by 20 points",35));
        //logger.info("Items added to the Shop");
        IItemDAO itemDAO = new ItemDAOImpl();
        List<Item> daoItems = itemDAO.getItems();
        //Collections.copy(items,daoItems);
        return daoItems;
    }
    @Override
    public int UserNumber() {
        return this.users.size();
    }
    @Override
    public int ItemNumber(){return this.items.size();}
    @Override
    public int LoggedNumber(){return this.logged.size();}
}