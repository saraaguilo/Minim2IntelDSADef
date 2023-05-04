package edu.upc.dsa;

import edu.upc.dsa.exceptions.EmailAlreadyInUseException;
import edu.upc.dsa.exceptions.IncorrectPasswordException;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.User;
import junit.framework.Assert;
import org.apache.log4j.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameManagerTest {

    private static Logger logger = Logger.getLogger(GameManagerTest.class);
    GameManager manager;

    @Before
    public void setUp() throws EmailAlreadyInUseException, IncorrectPasswordException, UserNotRegisteredException {
        this.manager = new GameManagerImpl();
        this.manager.register(new User("Toni","Boté","toni@upc.edu","12345"));
        this.manager.register(new User("Jordi","Pié","jordi@upc.edu","123"));
        this.manager.login("toni@upc.edu","12345");
    }

    @After
    public void tearDown()  {
        this.manager = null;
    }
    @Test
    public void registerUserTest() throws EmailAlreadyInUseException{
        this.manager.register(new User("Sara","Aguiló","sara@upc.edu","123456"));
        Assert.assertEquals(3,manager.UserNumber());;
    }
    @Test
    public void loginUserTest() throws IncorrectPasswordException, UserNotRegisteredException{
        this.manager.login("jordi@upc.edu","123");
        Assert.assertEquals(2,manager.LoggedNumber());;
    }
}
