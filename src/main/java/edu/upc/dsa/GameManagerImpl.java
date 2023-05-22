package edu.upc.dsa;

import edu.upc.dsa.CRUD.FactorySession;
import edu.upc.dsa.CRUD.Session;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
    public List<User> getUsers(){
        return users;
    }

    public GameManagerImpl() {
        this.users = new LinkedList<>();
        this.logged = new LinkedList<>();
        this.items = new LinkedList<>();
        usersMap = new HashMap<>();
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
        Session session = null;
        //User user1 = usersMap.get(user.getEmail());
        try {
            session = FactorySession.openSession();
            user = new User();
            logger.info("User registered");
            session.save(user);
        }
        catch (Exception e) {
            logger.info("Exception");
        }
        finally {
            session.close();
            logger.info("Session closed");
        }
        return user;
    }

    @Override
    public User login(Credentials credentials) throws UserNotRegisteredException, IncorrectPasswordException {
        Session session = null;
        try {
            session = FactorySession.openSession();
            HashMap<String, String> credentialsHash = new HashMap<>();
            credentialsHash.put("email", credentials.getEmail());
            credentialsHash.put("password", credentials.getPassword());
            List<Object> userMatch = session.findAll(User.class, credentialsHash);
            if (userMatch.size() == 1) {
                logger.info("Succesful login" + credentials.getEmail());
                User user = (User) userMatch.get(0);
                return user;
            } else if (userMatch.size() == 0) {
                logger.info("User not registered");
                throw new UserNotRegisteredException();
            }
        }
        catch (Exception e){
            logger.info("Something went wrong");
        }
        finally {
            session.close();
        }
            logger.warn("Incorrect user name or password");
            throw new IncorrectPasswordException();
        }
    public List<Item> Shop ()
    {
        items.add (new Item("Potion","Recover 50 health points",15));
        items.add(new Item("Sword","Increase damage by 20 points",35));
        logger.info("Items added to the Shop");
        return items;
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