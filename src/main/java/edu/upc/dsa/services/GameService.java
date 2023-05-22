package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.exceptions.EmailAlreadyInUseException;
import edu.upc.dsa.exceptions.IncorrectPasswordException;
import edu.upc.dsa.exceptions.UserNotRegisteredException;
import edu.upc.dsa.models.Credentials;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/game", description = "Endpoint to Game Service")
@Path("/game")
public class GameService {

    private GameManager manager;


    public GameService() throws EmailAlreadyInUseException {
        this.manager = GameManagerImpl.getInstance();
        //if (manager.getUsers().size()==0){
            //manager.register(new User("Toni","Boté","toni@upc.edu","12345"));
            //manager.register(new User("Jordi","Pié","jordi@upc.edu","123"));
            //manager.register(new User("Anna","Sabater","anna@upc.edu","1234"));
            //manager.register(new User("Sara","Aguiló","sara@upc.edu","123456"));
        //}
    }

    @POST
    @ApiOperation(value = "User registration", notes = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully registered", response = User.class),
            @ApiResponse(code = 404, message = "This email address is already in use"),
    })
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user) throws EmailAlreadyInUseException {
        try{
            this.manager.register(new User(user.getName(), user.getSurname(), user.getEmail(), user.getPassword()));
            return Response.status(201).entity(user).build();
        } catch (EmailAlreadyInUseException e){
            e.printStackTrace();
            return Response.status(404).entity(user).build();

        }

    }
    @POST
    @ApiOperation(value = "User login", notes = "Authenticate an existing user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully authenticated", response = User.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 401, message = "Incorrect password"),
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials) throws UserNotRegisteredException, IncorrectPasswordException {
        try {
            User user = this.manager.login(credentials);
            return Response.status(201).entity(user).build();
        } catch (UserNotRegisteredException e) {
            return Response.status(404).build();
        } catch (IncorrectPasswordException e) {
            return Response.status(401).build();
        }}



    @GET
    @ApiOperation(value = "View the items from the shop", notes = "View items")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/shop")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShop() {

        List<Item> items = this.manager.Shop();
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build()  ;
    }

}